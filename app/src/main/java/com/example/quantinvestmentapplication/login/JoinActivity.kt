package com.example.quantinvestmentapplication.login

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quantinvestmentapplication.R
import com.example.quantinvestmentapplication.api.BaseResponse
import com.example.quantinvestmentapplication.api.RetrofitInstance
import com.example.quantinvestmentapplication.api.dto.PostJoinReq
import com.example.quantinvestmentapplication.config.BaseActivityVB
import com.example.quantinvestmentapplication.databinding.ActivityJoinBinding
import com.example.quantinvestmentapplication.databinding.ActivityLoginBinding
import com.example.quantinvestmentapplication.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JoinActivity : BaseActivityVB<ActivityJoinBinding>(ActivityJoinBinding::inflate) {

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
        joinUser()

    }

    private fun joinUser() {
        binding.joinBtn.setOnClickListener{
            showLoading()
            val id = binding.joinIdEt.text.toString()
            val pw = binding.joinPwEt.text.toString()
            val accountNum = binding.joinAccountNumEt.text.toString()
            val appKey = binding.joinAppkeyEt.text.toString()
            val appSecret = binding.joinAppSecretEt.text.toString()

            if (id == "" || pw == "" || accountNum == "" || appKey == "" || appSecret == "") {
                showCustomToast("입력하신 정보가 올바르지 않습니다.")
                hideLoading()
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    val postJoinReq = PostJoinReq(id, pw, accountNum, appKey, appSecret)
                    Log.d("JoinActivity", postJoinReq.toString())
                    val response = joinUser(postJoinReq)
                    Log.d("JoinActivity", response.toString())

                    if (response.isSuccess) {
                        val sharedPreferences = getSharedPreferences("Quant", MODE_PRIVATE)
                        sharedPreferences.edit()
                            .putString(Constants.X_HAS_CREATED, "Y")
                            .apply()
                        withContext(Dispatchers.Main) {
                            hideLoading()
                            showCustomToast(response.result!!)
                        }
                        val intent = Intent(this@JoinActivity, LoginActivity::class.java)
                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                        finish()
                    } else {
                        Log.d("JoinActivity", "회원가입 실패")
                        withContext(Dispatchers.Main) {
                            hideLoading()
                            showCustomToast(response.message)
                        }
                    }
                }
            }

        }
    }

    private suspend fun joinUser(postJoinReq: PostJoinReq) : BaseResponse<String> {
        return RetrofitInstance.userApi.joinUser(postJoinReq)
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