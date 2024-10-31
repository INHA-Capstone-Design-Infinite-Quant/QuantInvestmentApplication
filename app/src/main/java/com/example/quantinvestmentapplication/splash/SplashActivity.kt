package com.example.quantinvestmentapplication.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quantinvestmentapplication.R
import com.example.quantinvestmentapplication.config.BaseActivityVB
import com.example.quantinvestmentapplication.databinding.ActivitySplashBinding
import com.example.quantinvestmentapplication.login.JoinActivity
import com.example.quantinvestmentapplication.login.LoginActivity
import com.example.quantinvestmentapplication.util.Constants

class SplashActivity : BaseActivityVB<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFullScreen()
        val sharedPreferences = getSharedPreferences("Quant", MODE_PRIVATE)
        val existUser = sharedPreferences.getString(Constants.X_HAS_CREATED, "N")

        if (existUser == "Y") {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, JoinActivity::class.java))
            finish()
        }
    }
}