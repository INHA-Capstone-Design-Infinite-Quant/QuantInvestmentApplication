package com.example.quantinvestmentapplication.api.dto

import com.google.gson.annotations.SerializedName

data class PostJoinReq(
    @SerializedName("id")
    val id : String, // 유저가 로그인에 사용하는 ID

    @SerializedName("password")
    val password : String,

    @SerializedName("accountNum")
    val accountNum : String, // 계좌번호

    @SerializedName("appKey")
    val appKey : String,

    @SerializedName("appSecret")
    val appSecret : String
)
