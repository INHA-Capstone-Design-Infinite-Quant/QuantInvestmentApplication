package com.example.quantinvestmentapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quantinvestmentapplication.config.BaseFragmentVB
import com.example.quantinvestmentapplication.databinding.FragmentChartBinding
import com.example.quantinvestmentapplication.databinding.FragmentLogBinding
import com.google.android.material.tabs.TabLayoutMediator

class LogFragment : BaseFragmentVB<FragmentLogBinding>(
    FragmentLogBinding::bind,
    R.layout.fragment_log
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}