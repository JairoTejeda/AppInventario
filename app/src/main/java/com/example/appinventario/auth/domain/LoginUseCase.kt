package com.example.appinventario.auth.domain

import com.example.appinventario.auth.data.network.request.LoginRequest
import com.example.appinventario.auth.data.network.response.LoginResponse
import com.example.appinventario.auth.data.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private  val authRepository: AuthRepository) {


    suspend operator fun invoke(loginRequest: LoginRequest):
            LoginResponse{
        return authRepository.login(loginRequest)
    }
}

