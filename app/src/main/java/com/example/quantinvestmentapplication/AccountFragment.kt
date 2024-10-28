package com.example.quantinvestmentapplication

import android.os.Bundle
import android.view.View
import com.example.quantinvestmentapplication.config.BaseFragmentVB
import com.example.quantinvestmentapplication.databinding.FragmentAccountBinding

class AccountFragment : BaseFragmentVB<FragmentAccountBinding>(
    FragmentAccountBinding::bind,
    R.layout.fragment_account
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}