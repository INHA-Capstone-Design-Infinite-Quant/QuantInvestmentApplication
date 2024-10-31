package com.example.quantinvestmentapplication.api

data class BaseResponse<T> (
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: T?
)