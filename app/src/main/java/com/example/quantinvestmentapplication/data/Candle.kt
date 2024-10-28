package com.example.quantinvestmentapplication.data

data class Candle(
    val createdAt: Int,
    val open: Float,
    val close: Float,
    val shadowHigh: Float,
    val shadowLow: Float
)
