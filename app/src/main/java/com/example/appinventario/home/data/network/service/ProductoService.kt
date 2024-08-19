package com.example.appinventario.home.data.network.service

import com.example.appinventario.core.retrofit.InventarioClient
import com.example.appinventario.home.data.network.request.CategoriaRequest
import com.example.appinventario.home.data.network.request.IngresoRequest
import com.example.appinventario.home.data.network.request.NuevoProductoResquest
import com.example.appinventario.home.data.network.request.SalidaRequest
import com.example.appinventario.home.data.network.response.CategoriaResponse
import com.example.appinventario.home.data.network.response.IngresoResponse
import com.example.appinventario.home.data.network.response.NuevoProductoResponse
import com.example.appinventario.home.data.network.response.ProductosResponse
import com.example.appinventario.home.data.network.response.SalidaResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductoService @Inject constructor(
    private val inventarioClient: InventarioClient) {

    suspend fun listarProductos(): List<ProductosResponse>{
        return withContext(Dispatchers.IO){
            val response = inventarioClient.listarProductos()
            response.body()!!
        }
    }

    suspend fun ingreso(
        ingresoRequest: IngresoRequest
    ): IngresoResponse{
        return withContext(Dispatchers.IO){
            val response = inventarioClient.ingreso(ingresoRequest)
            response.body()!!
        }
    }

    suspend fun salida(
        salidaRequest: SalidaRequest
    ): SalidaResponse{
        return withContext(Dispatchers.IO){
            val response = inventarioClient.salida(salidaRequest)
            response.body()!!
        }
    }

    suspend fun categoriar(
        categoriaRequest: CategoriaRequest
    ): CategoriaResponse{
        return withContext(Dispatchers.IO){
            val response = inventarioClient.categoriar(categoriaRequest)
            response.body()!!
        }
    }


    suspend fun nuevoprodu(
        nuevoproductoRequest: NuevoProductoResquest
    ): NuevoProductoResponse{
        return withContext(Dispatchers.IO){
            val response = inventarioClient.nuevoprodu(nuevoproductoRequest)
            response.body()!!
        }
    }

}