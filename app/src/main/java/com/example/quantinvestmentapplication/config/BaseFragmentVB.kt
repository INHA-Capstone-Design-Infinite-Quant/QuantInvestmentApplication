package com.example.quantinvestmentapplication.config

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragmentVB<B : ViewBinding>(
// B라는 ViewBinding 타입의 제네릭 타입 매개변수를 사용한다.

    private val bind: (View) -> B, // 뷰 바인딩 객체를 생성하는 람다식이다. 이 함수는 View를 입력으로 받아서 B 타입의 뷰 바인딩 객체를 반환한다.
    @LayoutRes layoutResId: Int, // Int 매개변수는 Fragment의 레이아웃 리소스 ID를 나타낸다. 이 레이아웃을 사용하여 Fragment의 UI를 초기화한다.
) : Fragment(layoutResId) { // 상위 클래스인 Fragment를 호출하여 해당 레이아웃 리소스 ID로 Fragment를 초기화한다.
    private var _binding: B? = null // _binding은 nullable한 B 타입의 뷰 바인딩 객체를 저장하는 프로퍼티이다.
    protected val binding get() = _binding!! // _binding 프로퍼티를 반환하는 읽기 전용 프로퍼티로, 뷰 바인딩 객체에 접근할 때 사용한다.


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // bind 함수를 호출하여 뷰 바인딩 객체를 생성하고 _binding 변수에 할당한다.
        // super.onCreateView를 호출하여 Fragment의 레이아웃을 inflate하고 이를 bind 함수의 입력으로 전달한다.
        _binding = bind(super.onCreateView(inflater, container, savedInstanceState)!!)
        return binding.root
    }


    fun showCustomToast(message: String) {
        val toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun onDestroyView() { // _binding 변수를 null로 설정하여 메모리 누수를 방지한다.
        super.onDestroyView()
        _binding = null
    }


}