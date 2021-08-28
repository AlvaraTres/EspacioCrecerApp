package com.example.espaciocrecer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.espaciocrecer.model.LoginForm
import com.example.espaciocrecer.model.User
import com.example.espaciocrecer.model.getLogedUserForm
import com.example.espaciocrecer.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    var loginResponse: MutableLiveData<Response<User>> = MutableLiveData()

    var getLogedUserResponse: MutableLiveData<Response<User>> = MutableLiveData()

    fun login(form: LoginForm) {
        viewModelScope.launch {
            val response = repository.login(form)
            loginResponse.value = response
        }
    }

    fun getLogedUser(form: getLogedUserForm) {
        viewModelScope.launch {
            val response = repository.getLogedUser(form)
            getLogedUserResponse.value = response
        }
    }
}