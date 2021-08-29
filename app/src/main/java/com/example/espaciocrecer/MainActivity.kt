package com.example.espaciocrecer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.espaciocrecer.api.LoginApi
import com.example.espaciocrecer.api.RetrofitInstance
import com.example.espaciocrecer.api.getLogedUserApi
import com.example.espaciocrecer.databinding.ActivityLoginBinding
import com.example.espaciocrecer.databinding.ActivityMainBinding
import com.example.espaciocrecer.model.User
import com.example.espaciocrecer.model.getLogedUserForm
import com.example.espaciocrecer.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}