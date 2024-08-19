package com.example.appinventario.core.retrofit

import com.example.appinventario.home.data.network.request.IngresoRequest
import com.example.appinventario.auth.data.network.request.LoginRequest
import com.example.appinventario.auth.data.network.request.RegistroRequest
import com.example.appinventario.home.data.network.response.IngresoResponse
import com.example.appinventario.auth.data.network.response.LoginResponse
import com.example.appinventario.auth.data.network.response.RegistroResponse
import com.example.appinventario.home.data.network.request.CategoriaRequest
import com.example.appinventario.home.data.network.request.NuevoProductoResquest
import com.example.appinventario.home.data.network.request.SalidaRequest
import com.example.appinventario.home.data.network.response.CategoriaResponse
import com.example.appinventario.home.data.network.response.NuevoProductoResponse
import com.example.appinventario.home.data.network.response.ProductosResponse
import com.example.appinventario.home.data.network.response.SalidaResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface InventarioClient {

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest):
            Response<LoginResponse>

    @POST("registrar")
    suspend fun registro(@Body request: RegistroRequest):
            Response<RegistroResponse>

    @GET("Productos")
    suspend fun listarProductos(): Response<List<ProductosResponse>>


    @POST("ingreso")
    suspend fun ingreso(@Body request: IngresoRequest):
            Response<IngresoResponse>

    @POST("salida")
    suspend fun salida(@Body request: SalidaRequest):
            Response<SalidaResponse>

    @POST("nuevacategoria")
    suspend fun categoriar(@Body request: CategoriaRequest):
            Response<CategoriaResponse>


    @POST("nuevoproducto")
    suspend fun nuevoprodu(@Body request: NuevoProductoResquest):
            Response<NuevoProductoResponse>
}