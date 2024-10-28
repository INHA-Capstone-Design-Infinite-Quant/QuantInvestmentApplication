package com.example.quantinvestmentapplication

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.quantinvestmentapplication.config.BaseFragmentVB
import com.example.quantinvestmentapplication.data.Candle
import com.example.quantinvestmentapplication.databinding.FragmentChartBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.random.Random
import java.time.LocalDate

class ChartFragment : BaseFragmentVB<FragmentChartBinding>(
    FragmentChartBinding::bind,
    R.layout.fragment_chart
) {

    private var itemNum = 0
    private var ticker: String? = "TQQQ"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initChart()
        val spinner = binding.tickerSpinner
        setSpinner(spinner)
        val mockCandles = generateMockCandles()
        setChartData(mockCandles)

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

    private fun generateMockCandles(): ArrayList<Candle> {
        val candles = ArrayList<Candle>()

        // 시작 가격을 랜덤으로 설정
        var previousClose = Random.nextInt(1000, 2001).toFloat() // 1000부터 2000 사이의 랜덤값

        for (i in 1..30) {  // 30일치 데이터 생성
            // 현재 시가는 이전 종가에 약간의 변동을 줘서 결정
            val open = previousClose + Random.nextInt(-10, 11).toFloat() // 이전 종가에 -10에서 +10 사이의 변동 추가

            // 종가는 시가에서 랜덤한 범위로 변동
            val close = open + Random.nextInt(-15, 16).toFloat() // -15에서 +15 사이의 변동 추가

            // 고가는 시가, 종가 중에서 높은 값 + 추가 변동
            val high = maxOf(open, close) + Random.nextInt(5, 21).toFloat() // 5에서 20 사이의 추가 변동

            // 저가는 시가, 종가 중에서 낮은 값 - 추가 변동
            val low = minOf(open, close) - Random.nextInt(5, 21).toFloat() // 5에서 20 사이의 추가 변동

            // 생성한 캔들 추가
            candles.add(
                Candle(
                    createdAt = i,
                    shadowHigh = high,
                    shadowLow = low,
                    open = open,
                    close = close
                )
            )

            // 다음 캔들을 위한 이전 종가 업데이트
            previousClose = close
        }

        return candles
    }



    private fun initChart() {
        binding.apply {
            priceChart.description.isEnabled = false
            priceChart.setMaxVisibleValueCount(200)
            priceChart.setPinchZoom(false)
            priceChart.setDrawGridBackground(false)
            // x축 설정
            priceChart.xAxis.apply {
                textColor = Color.BLACK
                position = XAxis.XAxisPosition.BOTTOM
                // 세로선 표시 여부 설정
                this.setDrawGridLines(true)
                axisLineColor = Color.rgb(50, 59, 76)
                gridColor = Color.rgb(50, 59, 76)
            }
            // 왼쪽 y축 설정
            priceChart.axisLeft.apply {
                textColor = Color.BLACK
                isEnabled = false
            }
            // 오른쪽 y축 설정
            priceChart.axisRight.apply {
                setLabelCount(7, false)
                textColor = Color.BLACK
                // 가로선 표시 여부 설정
                setDrawGridLines(true)
                // 차트의 오른쪽 테두리 라인 설정
                setDrawAxisLine(true)
                axisLineColor = Color.rgb(50, 59, 76)
                gridColor = Color.rgb(50, 59, 76)
            }
            priceChart.legend.isEnabled = false
        }
    }

    private fun setChartData(candles: ArrayList<Candle>) {
        val priceEntries = ArrayList<CandleEntry>()
        for (candle in candles) {
            // 캔들 차트 entry 생성
            priceEntries.add(
                CandleEntry(
                    candle.createdAt.toFloat(),
                    candle.shadowHigh,
                    candle.shadowLow,
                    candle.open,
                    candle.close
                )
            )
        }

        val priceDataSet = CandleDataSet(priceEntries, "").apply {
            axisDependency = YAxis.AxisDependency.LEFT
            // 심지 부분 설정
            shadowColor = Color.LTGRAY
            shadowWidth = 0.7F
            // 음봉 설정
            decreasingColor = Color.rgb(18, 98, 197)
            decreasingPaintStyle = Paint.Style.FILL
            // 양봉 설정
            increasingColor = Color.rgb(200, 74, 49)
            increasingPaintStyle = Paint.Style.FILL

            neutralColor = Color.rgb(6, 18, 34)
            setDrawValues(false)
            // 터치시 노란 선 제거
            highLightColor = Color.TRANSPARENT
        }

        binding.priceChart.apply {
            this.data = CandleData(priceDataSet)
            invalidate()
        }
    }

}