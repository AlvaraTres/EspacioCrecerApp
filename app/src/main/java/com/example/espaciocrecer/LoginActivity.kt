package com.example.espaciocrecer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.espaciocrecer.model.LoginForm
import com.example.espaciocrecer.model.User
import com.example.espaciocrecer.repository.Repository
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

class LoginActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    private lateinit var viewModel: MainViewModel

    private var email_form: String = ""
    private var password_form: String = ""
    public lateinit var user : User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Toast.makeText(this@LoginActivity, "Layout cargado", Toast.LENGTH_SHORT).show()

        val repository = Repository()

        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)



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
                    val myForm = LoginForm(email_form, password_form)
                    viewModel.login(myForm)
                    viewModel.loginResponse.observe(this, { response ->
                        if(response.isSuccessful){
                            Log.d("Main", response.body().toString())
                            val jsonUser = response.body()
                            val userId = jsonUser?.id ?: 0
                            if(userId != null || userId > 0){
                                val id_users_rol = jsonUser?.id_users_rol ?: 0
                                val rut_usuario = jsonUser?.rut_usuario ?: "null"
                                val nombre_usuario = jsonUser?.nombre_usuario ?: "null"
                                val apellido_pat_usuario = jsonUser?.apellido_pat_usuario ?: "null"
                                val apellido_mat_usuario = jsonUser?.apellido_mat_usuario ?: "null"
                                val sexo = jsonUser?.sexo ?: "null"
                                val telefono = jsonUser?.telefono ?: "null"
                                val email = jsonUser?.email ?: "null"
                                val formacion = jsonUser?.formacion ?: "null"
                                val fecha_nacimiento = jsonUser?.formacion ?: "null"
                                val password = jsonUser?.password ?: "null"
                                user = User(userId, id_users_rol, rut_usuario, nombre_usuario, apellido_pat_usuario, apellido_mat_usuario, sexo, telefono, email, formacion, fecha_nacimiento, password)
                            }else{
                                Toast.makeText(this@LoginActivity, "Correo o contraseña son incorrectos.", Toast.LENGTH_SHORT).show()
                            }

                        }else{
                            Toast.makeText(this@LoginActivity, "Fallo en la conexión, favor comprueba tu conexión a internet", Toast.LENGTH_SHORT).show()
                        }
                    })
                }else{
                    Toast.makeText(this@LoginActivity, "Contraseña vacia", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this@LoginActivity, "Por favor ingrese su correo", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createApiService() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://faa0-190-46-212-90.ngrok.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun executeLogin(email: String, password: String){

    }

}