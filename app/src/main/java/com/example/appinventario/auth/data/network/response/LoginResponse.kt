package com.example.appinventario.auth.data.network.response

data class LoginResponse (
    var rpta: Boolean,
    var id:String,
    var nombre: String,
    var emailusu: String,
    var celularusu:String,
    var usuariousu:String,
    var password: String,
    var rolusu: String,
    var mensaje: String
)

