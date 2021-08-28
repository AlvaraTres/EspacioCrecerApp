package com.example.espaciocrecer.repository

import com.example.espaciocrecer.api.RetrofitInstance
import com.example.espaciocrecer.model.LoginForm
import com.example.espaciocrecer.model.User
import retrofit2.Response

class Repository {

    suspend fun login(form: LoginForm): Response<User> {
        return RetrofitInstance.api.login(form)
    }
}