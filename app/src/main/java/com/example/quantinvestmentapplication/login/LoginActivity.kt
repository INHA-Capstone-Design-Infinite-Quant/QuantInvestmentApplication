package com.example.quantinvestmentapplication.login

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.quantinvestmentapplication.api.BaseResponse
import com.example.quantinvestmentapplication.api.RetrofitInstance
import com.example.quantinvestmentapplication.api.dto.PostJoinReq
import com.example.quantinvestmentapplication.api.dto.PostLoginReq
import com.example.quantinvestmentapplication.config.BaseActivityVB
import com.example.quantinvestmentapplication.databinding.ActivityLoginBinding
import com.example.quantinvestmentapplication.main.MainActivity
import com.example.quantinvestmentapplication.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : BaseActivityVB<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    private val loadingDialog: Dialog by lazy {
        ProgressDialog(this).apply {
            setMessage("Now Loading...")
            setCancelable(false)
            setCanceledOnTouchOutside(false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFullScreen()
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.loginNextBtn.setOnClickListener{
            showLoading()
            val id = binding.loginIdEt.text.toString()
            val pw = binding.loginPwEt.text.toString()
            if (id == "" || pw == "") {
                showCustomToast("입력하신 정보가 올바르지 않습니다.")
                hideLoading()
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    val postLoginReq = PostLoginReq(id, pw)
                    Log.d("LoginActivity", postLoginReq.toString())
                    val response = loginUser(postLoginReq)
                    Log.d("LoginActivity", response.toString())

                    if (response.isSuccess) {
                        val accessToken: String = response.result!!
                        Log.d("accessToken", accessToken)

                        val sharedPreferences = getSharedPreferences("Quant", MODE_PRIVATE)
                        sharedPreferences.edit()
                            .putString(Constants.X_ACCESS_TOKEN, "Bearer $accessToken")
                            .apply()

                        withContext(Dispatchers.Main) {
                            hideLoading()
                            showCustomToast("로그인에 성공하였습니다.")
                        }
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                        finish()
                    } else {
                        Log.d("LoginActivity", "로그인 실패")
                        withContext(Dispatchers.Main) {
                            hideLoading()
                            showCustomToast(response.message)
                        }
                    }

                }
            }
        }

        binding.joinNextBtn.setOnClickListener{
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private suspend fun loginUser(postLoginReq: PostLoginReq) : BaseResponse<String> {
        return RetrofitInstance.userApi.loginUser(postLoginReq)
    }

    private fun showLoading() {
        if (!loadingDialog.isShowing) {
            loadingDialog.show()
        }
    }

    private fun hideLoading() {
        if (loadingDialog.isShowing) {
            loadingDialog.dismiss()
        }
    }
}