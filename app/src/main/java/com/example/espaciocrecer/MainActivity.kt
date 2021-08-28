package com.example.espaciocrecer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.espaciocrecer.databinding.ActivityLoginBinding
import com.example.espaciocrecer.databinding.ActivityMainBinding
import com.example.espaciocrecer.model.getLogedUserForm
import com.example.espaciocrecer.repository.Repository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = GetLogedUserModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getLogedUser(getLogedUserForm(1))
        viewModel.getLogedUserResponse.observe(this, Observer{ response ->
            Log.d("response", response.body().toString())
        })

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}