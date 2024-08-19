package com.example.appinventario.home.domain

import com.example.appinventario.home.data.network.response.ProductosResponse
import com.example.appinventario.home.data.repository.ProductoRepository
import javax.inject.Inject

class ProductoUseCase @Inject constructor(
    private val repository: ProductoRepository
) {
    suspend operator fun invoke(): List<ProductosResponse>{
        return repository.listarProducto()
    }


}