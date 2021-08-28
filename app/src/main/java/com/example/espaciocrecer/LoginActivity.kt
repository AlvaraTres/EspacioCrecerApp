package com.example.espaciocrecer

import API.ApiService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.UserHandle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.net.PasswordAuthentication

class LoginActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    private lateinit var retrofit: Retrofit
    private lateinit var service: ApiService
    private lateinit var user: User
    private var email_form: String = ""
    private var password_form: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        service = createApiService()

        toolbar = findViewById(R.id.toolbar_login)
        setSupportActionBar(toolbar)

        email.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_email_24, 0, 0, 0)
        password.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_lock_24, 0,0,0)

        remember_password.setOnClickListener(View.OnClickListener {
            if(!(remember_password.isSelected)){
                remember_password.isChecked = true
                remember_password.isSelected = true
            }else{
                remember_password.isChecked = false
                remember_password.isSelected = false
            }
        })

        val emailInput =  findViewById<EditText>(R.id.email)
        val passwordInput = findViewById<EditText>(R.id.password)

        val loginBtn = findViewById<Button>(R.id.login_button)

        loginBtn.setOnClickListener {
            email_form = emailInput.text.toString().trim()
            password_form = passwordInput.text.toString().trim()
            if(email_form.isNotEmpty()){
                if(password_form.isNotEmpty()){
                    executeLogin(email_form, password_form)
                }else{
                    Toast.makeText(this@LoginActivity, "Contraseña vacia", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this@LoginActivity, "Por favor ingrese su correo", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createApiService() : ApiService {
        retrofit = Retrofit.Builder().baseUrl("http://faa0-190-46-212-90.ngrok.io/").addConverterFactory(
            GsonConverterFactory.create()
        ).build()

        return retrofit.create(ApiService::class.java)
    }

    private fun executeLogin(email: String, password: String){
        val call = service.login(email, password)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>){
                if(response.isSuccessful && response.body() != null){
                    try{
                        Toast.makeText(this@LoginActivity, "Login Correcto", Toast.LENGTH_SHORT).show()
                    }catch (e: Exception) {
                        Log.d("login", e.toString())
                        Toast.makeText(this@LoginActivity, response.body(), Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("login",t.toString())
            }
        })
    }
}