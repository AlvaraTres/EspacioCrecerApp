package com.example.espaciocrecer.api

import com.example.espaciocrecer.model.LoginForm
import com.example.espaciocrecer.model.User
import com.example.espaciocrecer.model.getLogedUserForm
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface getLogedUserApi {

    @GET("getLogedUser")
    suspend fun getLogedUser(
        @Body form: getLogedUserForm,
    ): Response<User>
}