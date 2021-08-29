package com.example.espaciocrecer.api

import com.example.espaciocrecer.model.LoginForm
import com.example.espaciocrecer.model.User
import com.example.espaciocrecer.model.getLogedUserForm
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface getLogedUserApi {

    @GET("getLogedUser/{id}")
    suspend fun getLogedUser(
        @Path("id") id : Int
    ) : Response<User>
}