package com.example.appinventario.home.domain

import com.example.appinventario.home.data.network.request.NuevoProductoResquest
import com.example.appinventario.home.data.network.response.NuevoProductoResponse
import com.example.appinventario.home.data.repository.ProductoRepository
import javax.inject.Inject

class NuevoProductoUseCase @Inject constructor(
    private val repository: ProductoRepository) {

    suspend operator fun invoke(nuevoproductoRequest: NuevoProductoResquest): NuevoProductoResponse {
        return repository.nuevoprodu(nuevoproductoRequest)
    }

}