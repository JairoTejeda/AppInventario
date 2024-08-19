package com.example.appinventario.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appinventario.core.util.Evento
import com.example.appinventario.home.data.network.request.CategoriaRequest
import com.example.appinventario.home.data.network.response.CategoriaResponse
import com.example.appinventario.home.domain.CategoriaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriaViewModel @Inject constructor(
    private val categoriaUseCase: CategoriaUseCase
) : ViewModel(){


    private val _nombre= MutableLiveData<String>()
    val nombre : LiveData<String> = _nombre

    private val _categoriaResponse = MutableLiveData<Evento<CategoriaResponse>>()
    val categoriaResponse: LiveData<Evento<CategoriaResponse>> = _categoriaResponse

    fun onRegistroc(
        nombre: String,
    ){

        _nombre.value = nombre

    }

    fun limpiarCampos(){
        _nombre.value = ""
    }
    fun registrocategoria(){
        viewModelScope.launch {
            val response = categoriaUseCase(
                CategoriaRequest(
                    nombre.value!!
                )
            )
            //Log.i("RESPONSE", response.toString())
            _categoriaResponse.value = Evento(response)
        }
    }

}