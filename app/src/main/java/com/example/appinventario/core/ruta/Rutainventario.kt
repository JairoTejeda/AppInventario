package com.example.appinventario.core.ruta

sealed class Rutainventario (val path:String) {
    object loginScreen: Rutainventario("loginScreen")
    object registroScreen: Rutainventario("registroScreen")
    object homeScreen: Rutainventario("homeScreen")
    object productoScreen: Rutainventario("productoScreen")
    object categoriaScreen: Rutainventario("categoriaScreen")
    object ingresoScreen: Rutainventario("ingresoScreen")
    object salidaScreen: Rutainventario("salidaScreen")
    object menuScreen: Rutainventario("menuScreen")
    object nuevoproductoScreen: Rutainventario("nuevoproductoScreen")





}