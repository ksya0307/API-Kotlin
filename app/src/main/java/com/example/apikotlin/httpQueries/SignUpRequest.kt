package com.example.apikotlin.httpQueries

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("email")
    val email:String,
    @SerializedName("password")
    val password:String,
    @SerializedName("firstName")
    val firstName:String,
    @SerializedName("lastName")
    val lastName:String

)