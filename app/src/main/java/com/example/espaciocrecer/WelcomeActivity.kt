package com.example.espaciocrecer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.espaciocrecer.model.getLogedUserForm
import com.example.espaciocrecer.repository.Repository

class WelcomeActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val getLoginIntent = getIntent()
        val userId = getLoginIntent.getIntExtra("userId", 0)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getUserReservas(userId)
        viewModel.getUserReservasResponse.observe(this, Observer { response ->
            if(response.isSuccessful){
                Log.d("UserLoged", response.body().toString())
            }else{
                Log.d("Error",  "error en la comunicación")
            }

        })


    }
}