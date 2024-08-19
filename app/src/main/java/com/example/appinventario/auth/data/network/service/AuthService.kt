package com.example.appinventario.auth.data.network.service

import com.example.appinventario.home.data.network.request.IngresoRequest
import com.example.appinventario.auth.data.network.request.LoginRequest
import com.example.appinventario.auth.data.network.request.RegistroRequest
import com.example.appinventario.home.data.network.response.IngresoResponse
import com.example.appinventario.auth.data.network.response.LoginResponse
import com.example.appinventario.auth.data.network.response.RegistroResponse
import com.example.appinventario.core.retrofit.InventarioClient

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthService @Inject
    constructor(private val inventarioClient: InventarioClient) {
        suspend fun login(loginRequest: LoginRequest): LoginResponse{
            return withContext(Dispatchers.IO){
                val response = inventarioClient.login(loginRequest)
                response.body()!!
            }
        }

    suspend fun registro(request: RegistroRequest): RegistroResponse{
        return withContext(Dispatchers.IO){
            val response = inventarioClient.registro(request)
            response.body()!!
        }
    }

}