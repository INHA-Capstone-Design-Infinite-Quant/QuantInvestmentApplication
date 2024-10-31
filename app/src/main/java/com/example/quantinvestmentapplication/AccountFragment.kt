package com.example.quantinvestmentapplication

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.quantinvestmentapplication.config.BaseFragmentVB
import com.example.quantinvestmentapplication.databinding.FragmentAccountBinding

class AccountFragment : BaseFragmentVB<FragmentAccountBinding>(
    FragmentAccountBinding::bind,
    R.layout.fragment_account
) {

    private var itemNum = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinner = binding.tickerSpinner
        setSpinner(spinner)
    }

    private fun setSpinner(spinner: Spinner) {
        val items = listOf("TQQQ", "SOXL")
        // 어댑터 생성 및 설정
        val adapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // 스피너 선택 이벤트 리스너 설정
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                itemNum = position
                // Ticker 선택에 따른 로직 처리
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

    }
}