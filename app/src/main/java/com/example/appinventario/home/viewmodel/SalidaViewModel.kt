package com.example.appinventario.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appinventario.core.util.Evento
import com.example.appinventario.home.data.network.request.SalidaRequest
import com.example.appinventario.home.data.network.response.SalidaResponse
import com.example.appinventario.home.domain.SalidaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SalidaViewModel @Inject constructor(
    private val salidaUseCase: SalidaUseCase
) : ViewModel(){

    private val _productoId = MutableLiveData<String>()
    val productoId : LiveData<String> = _productoId
    private val _cantidad = MutableLiveData<String>()
    val cantidad : LiveData<String> = _cantidad

    private val _salidaResponse = MutableLiveData<Evento<SalidaResponse>>()
    val salidaResponse: LiveData<Evento<SalidaResponse>> = _salidaResponse

    fun onSRegistro(
        productoId: String,
        cantidad: String,
    ){
        _productoId.value = productoId
        _cantidad.value = cantidad

    }

    fun setearFormularioRegistro(){
        _productoId.value = ""
        _cantidad.value = ""
    }
    fun registrosalidaproducto(){
        viewModelScope.launch {
            val response = salidaUseCase(
                SalidaRequest(
                    productoId.value!!,
                    cantidad.value!!
                )
            )
            //Log.i("RESPONSE", response.toString())
            _salidaResponse.value = Evento(response)
        }
    }


}
