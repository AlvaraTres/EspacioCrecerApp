package com.example.espaciocrecer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.espaciocrecer.repository.Repository

class GetLogedUserModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}