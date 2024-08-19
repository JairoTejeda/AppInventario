package com.example.appinventario.auth.domain

import com.example.appinventario.auth.data.network.request.RegistroRequest
import com.example.appinventario.auth.data.network.response.RegistroResponse
import com.example.appinventario.auth.data.repository.AuthRepository
import javax.inject.Inject

class RegistroUseCase @Inject constructor(
    private val repository: AuthRepository){

    suspend operator fun invoke(registroRequest: RegistroRequest):
            RegistroResponse{
        return  repository.registro(registroRequest)
    }

}