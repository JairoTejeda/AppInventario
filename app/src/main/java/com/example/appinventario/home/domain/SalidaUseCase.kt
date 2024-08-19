package com.example.appinventario.home.domain

import com.example.appinventario.home.data.network.request.SalidaRequest
import com.example.appinventario.home.data.network.response.SalidaResponse
import com.example.appinventario.home.data.repository.ProductoRepository
import javax.inject.Inject

class SalidaUseCase @Inject constructor(
    private val repository: ProductoRepository) {

    suspend operator fun invoke(salidaRequest: SalidaRequest): SalidaResponse {
        return repository.salida(salidaRequest)
    }
}