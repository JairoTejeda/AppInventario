package com.example.appinventario.home.data.repository

import com.example.appinventario.home.data.network.request.CategoriaRequest
import com.example.appinventario.home.data.network.request.IngresoRequest
import com.example.appinventario.home.data.network.request.NuevoProductoResquest
import com.example.appinventario.home.data.network.request.SalidaRequest
import com.example.appinventario.home.data.network.response.CategoriaResponse
import com.example.appinventario.home.data.network.response.IngresoResponse
import com.example.appinventario.home.data.network.response.NuevoProductoResponse
import com.example.appinventario.home.data.network.response.ProductosResponse
import com.example.appinventario.home.data.network.response.SalidaResponse
import com.example.appinventario.home.data.network.service.ProductoService
import javax.inject.Inject

class ProductoRepository @Inject constructor(
    private val productoService: ProductoService
) {
    suspend fun listarProducto(): List<ProductosResponse>{
        return productoService.listarProductos()
    }

    suspend fun ingreso (ingresoRequest: IngresoRequest): IngresoResponse {
        return productoService.ingreso(ingresoRequest)

    }

    suspend fun salida (salidaRequest: SalidaRequest): SalidaResponse {
        return productoService.salida(salidaRequest)

    }

    suspend fun categoriar (categoriaRequest: CategoriaRequest): CategoriaResponse {
        return productoService.categoriar(categoriaRequest)

    }

    suspend fun nuevoprodu (nuevoproductoResquest: NuevoProductoResquest): NuevoProductoResponse {
        return productoService.nuevoprodu(nuevoproductoResquest)

    }
}