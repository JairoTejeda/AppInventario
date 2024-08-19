package com.example.appinventario.home.domain


import com.example.appinventario.home.data.network.request.IngresoRequest
import com.example.appinventario.home.data.network.response.IngresoResponse
import com.example.appinventario.home.data.repository.ProductoRepository
import javax.inject.Inject

class IngresoUseCase @Inject constructor(
    private val repository: ProductoRepository) {

    suspend operator fun invoke(ingresoRequest: IngresoRequest): IngresoResponse{
        return repository.ingreso(ingresoRequest)
    }

}