package com.example.quantinvestmentapplication.main

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import com.example.quantinvestmentapplication.AccountFragment
import com.example.quantinvestmentapplication.ChartFragment
import com.example.quantinvestmentapplication.LogFragment
import com.example.quantinvestmentapplication.R
import com.example.quantinvestmentapplication.data.Candle
import com.example.quantinvestmentapplication.config.BaseActivityVB
import com.example.quantinvestmentapplication.databinding.ActivityMainBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry

class MainActivity : BaseActivityVB<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFullScreen()
        initBottomNavigation()

    }

    private fun initBottomNavigation(){

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, ChartFragment())
            .commitAllowingStateLoss()

        binding.mainBnv.setOnItemSelectedListener{ item ->
            when (item.itemId) {

                R.id.chartFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, ChartFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }

                R.id.accountFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, AccountFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.logFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, LogFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}