package com.example.apikotlin

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private lateinit var apiService: Api

    fun getApiService():Api{
        val base_url = "http://cinema.areas.su"

        if(!::apiService.isInitialized){
            val retrofit = Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            apiService = retrofit.create(Api::class.java)
        }

        return apiService
    }
}