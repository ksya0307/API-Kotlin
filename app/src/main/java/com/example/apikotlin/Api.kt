package com.example.apikotlin


import com.example.apikotlin.httpQueries.LoginRequest
import com.example.apikotlin.httpQueries.LoginResponse
import com.example.apikotlin.httpQueries.SignUpRequest
import com.example.apikotlin.httpQueries.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface Api {
    @POST("auth/login")
    fun auth(@Body request: LoginRequest): Call<LoginResponse>

    @POST("auth/register")
    fun signUp(@Body request: SignUpRequest): Call<Void>
}