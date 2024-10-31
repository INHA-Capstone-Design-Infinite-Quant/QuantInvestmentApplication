package com.example.quantinvestmentapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.quantinvestmentapplication.config.BaseFragmentVB
import com.example.quantinvestmentapplication.databinding.FragmentLogBinding
import com.example.quantinvestmentapplication.databinding.FragmentMypageBinding

class MypageFragment : BaseFragmentVB<FragmentMypageBinding>(
    FragmentMypageBinding::bind,
    R.layout.fragment_mypage
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}