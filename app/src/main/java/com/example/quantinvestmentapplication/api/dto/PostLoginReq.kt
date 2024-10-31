package com.example.quantinvestmentapplication.api.dto

import com.google.gson.annotations.SerializedName

data class PostLoginReq(
    @SerializedName("id")
    val id : String, // 유저가 로그인에 사용하는 ID

    @SerializedName("password")
    val password : String
)
