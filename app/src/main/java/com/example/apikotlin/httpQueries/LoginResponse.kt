package com.example.apikotlin.httpQueries

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("token")
    val token:Int)


