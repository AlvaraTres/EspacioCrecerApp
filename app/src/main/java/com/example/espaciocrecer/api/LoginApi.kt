package com.example.espaciocrecer.api

import com.example.espaciocrecer.model.LoginForm
import com.example.espaciocrecer.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("login")
    suspend fun login(
        @Body form: LoginForm,
    ): Response<User>

}