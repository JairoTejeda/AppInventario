package com.example.appinventario.home.data.network.request

data class NuevoProductoResquest(
    var modelo: String,
    var precio: String,
    var talla: String,
    var cantidad: String,
    var zku: String,
    var urlimagen: String,
    var idcategoria: String,
)
