package com.example.quantinvestmentapplication.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.quantinvestmentapplication.config.BaseActivityVB
import com.example.quantinvestmentapplication.databinding.ActivityLoginBinding
import com.example.quantinvestmentapplication.main.MainActivity

class LoginActivity : BaseActivityVB<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFullScreen()
        binding.loginNextBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }
    }
}