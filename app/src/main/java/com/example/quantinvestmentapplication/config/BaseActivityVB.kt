package com.example.quantinvestmentapplication.config

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding


abstract class BaseActivityVB<B : ViewBinding>(private val inflate: (LayoutInflater) -> B) :
    AppCompatActivity() {
    // B라는 제네릭 타입 매개변수를 사용한다. ViewBinding 객체를 inflate하고 초기화하기 위한 람다식 inflate를 매개변수로 받는다.
    // inflate 변수의 타입은 (LayoutInflater) -> B 이다. 즉, 람다식의 입력 매개변수로 LayoutInflater 객체를 받는다.
    // Activity 생성을 위한 사용자 정의 추상 클래스이므로 AppCompatActivity를 확장한다.
    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun showCustomToast(message: String) { // 입력 파라미터를 토스트 메시지로 출력한다.
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.show()
    }

    fun setFullScreen() {
        window.apply {
            statusBarColor = android.graphics.Color.TRANSPARENT
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }
}