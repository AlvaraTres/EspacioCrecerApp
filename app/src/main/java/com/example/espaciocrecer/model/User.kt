package com.example.espaciocrecer.model

data class User(
    var id: Int,
    var id_users_rol: Int,
    var rut_usuario: String,
    var nombre_usuario: String,
    var apellido_pat_usuario: String,
    var apellido_mat_usuario: String,
    var sexo: String,
    var telefono: String,
    var email: String,
    var formacion: String,
    var fecha_nacimiento: String,
    var password: String
)
