package com.example.appinventario.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appinventario.home.data.network.request.IngresoRequest
import com.example.appinventario.home.data.network.response.IngresoResponse
import com.example.appinventario.home.domain.IngresoUseCase
import com.example.appinventario.core.util.Evento
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IngresoViewModel @Inject constructor(
    private val ingresoUseCase: IngresoUseCase
) : ViewModel(){

    private val _productoId = MutableLiveData<String>()
    val productoId : LiveData<String> = _productoId
    private val _cantidad = MutableLiveData<String>()
    val cantidad : LiveData<String> = _cantidad

    private val _ingresoResponse = MutableLiveData<Evento<IngresoResponse>>()
    val ingresoResponse: LiveData<Evento<IngresoResponse>> = _ingresoResponse

    fun onRegistro(
        productoId: String,
        cantidad: String,
    ){
        _productoId.value = productoId
        _cantidad.value = cantidad

    }

    fun limpiarCampos(){
        _productoId.value = ""
        _cantidad.value = ""
    }
    fun registroingresoproducto(){
        viewModelScope.launch {
            val response = ingresoUseCase(
                IngresoRequest(
                    productoId.value!!,
                    cantidad.value!!
                )
            )
            //Log.i("RESPONSE", response.toString())
            _ingresoResponse.value = Evento(response)
        }
    }


}