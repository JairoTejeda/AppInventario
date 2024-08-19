package com.example.appinventario.home.domain

import com.example.appinventario.home.data.network.request.CategoriaRequest
import com.example.appinventario.home.data.network.response.CategoriaResponse
import com.example.appinventario.home.data.repository.ProductoRepository
import javax.inject.Inject

class CategoriaUseCase @Inject constructor(
    private val repository: ProductoRepository) {


    suspend operator fun invoke(categoriaRequest: CategoriaRequest): CategoriaResponse {
        return repository.categoriar(categoriaRequest)
    }
}