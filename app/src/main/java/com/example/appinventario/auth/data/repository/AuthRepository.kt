package com.example.appinventario.auth.data.repository

import com.example.appinventario.home.data.network.request.IngresoRequest
import com.example.appinventario.auth.data.network.request.LoginRequest
import com.example.appinventario.auth.data.network.request.RegistroRequest
import com.example.appinventario.home.data.network.response.IngresoResponse
import com.example.appinventario.auth.data.network.response.LoginResponse
import com.example.appinventario.auth.data.network.response.RegistroResponse
import com.example.appinventario.auth.data.network.service.AuthService
import javax.inject.Inject

class AuthRepository  @Inject constructor(
    private val authService: AuthService){

    suspend fun login (loginRequest: LoginRequest): LoginResponse{
        return  authService.login(loginRequest)
    }

    suspend fun registro (registroRequest: RegistroRequest): RegistroResponse{
        return authService.registro(registroRequest)
    }
}