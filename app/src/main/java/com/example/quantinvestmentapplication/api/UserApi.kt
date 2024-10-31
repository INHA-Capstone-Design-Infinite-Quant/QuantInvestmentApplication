package com.example.quantinvestmentapplication.api

import com.example.quantinvestmentapplication.api.dto.PostJoinReq
import com.example.quantinvestmentapplication.api.dto.PostLoginReq
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("/api/users/join")
    suspend fun joinUser(@Body postJoinReq: PostJoinReq): BaseResponse<String>

    @POST("/api/users/login")
    suspend fun loginUser(@Body postLoginReq: PostLoginReq): BaseResponse<String>

}