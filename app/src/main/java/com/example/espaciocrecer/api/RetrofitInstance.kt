package com.example.espaciocrecer.api

import com.example.espaciocrecer.util.Constants.Companion.RETROFIT_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(RETROFIT_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    val api: LoginApi by lazy {
        retrofit.create(LoginApi::class.java)
    }

    val api2: getLogedUserApi by lazy {
        retrofit.create(getLogedUserApi::class.java)
    }
}