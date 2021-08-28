package com.example.espaciocrecer.repository

import com.example.espaciocrecer.api.RetrofitInstance
import com.example.espaciocrecer.model.LoginForm
import com.example.espaciocrecer.model.User
import com.example.espaciocrecer.model.getLogedUserForm
import retrofit2.Response

class Repository {

    suspend fun login(form: LoginForm): Response<User> {
        return RetrofitInstance.api.login(form)
    }

    suspend fun getLogedUser(form: getLogedUserForm): Response<User> {
        return RetrofitInstance.api2.getLogedUser(form)
    }
}