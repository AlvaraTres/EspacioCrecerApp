package com.example.espaciocrecer

data class User(
    val id: Int,
    val id_users_rol: Int,
    val rut_usuario: String,
    val nombre_usuario: String,
    val apellido_pat_usuario: String,
    val apellido_mat_usuario: String,
    val sexo: String,
    val telefono: String,
    val email: String,
    val formacion: String,
    val fecha_nacimiento: String,
    val password: String
)
