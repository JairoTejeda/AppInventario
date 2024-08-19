package com.example.appinventario.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appinventario.core.util.Evento
import com.example.appinventario.home.data.network.request.CategoriaRequest
import com.example.appinventario.home.data.network.request.NuevoProductoResquest
import com.example.appinventario.home.data.network.response.CategoriaResponse
import com.example.appinventario.home.data.network.response.NuevoProductoResponse
import com.example.appinventario.home.domain.NuevoProductoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NuevoProductoViewModel @Inject constructor(
    private val nuevoproductoUsecase : NuevoProductoUseCase
) : ViewModel(){


    private val _modelo= MutableLiveData<String>()
    val modelo : LiveData<String> = _modelo


    private val _precio= MutableLiveData<String>()
    val precio : LiveData<String> = _precio

    private val _talla= MutableLiveData<String>()
    val talla : LiveData<String> = _talla

    private val _cantidad= MutableLiveData<String>()
    val cantidad : LiveData<String> = _cantidad

    private val _zku= MutableLiveData<String>()
    val zku : LiveData<String> = _zku

    private val _urlimagen= MutableLiveData<String>()
    val urlimagen : LiveData<String> = _urlimagen

    private val _idcategoria= MutableLiveData<String>()
    val idcategoria : LiveData<String> = _idcategoria



    private val _nuevoproductoResponse = MutableLiveData<Evento<NuevoProductoResponse>>()
    val nuevoproductoResponse: LiveData<Evento<NuevoProductoResponse>> = _nuevoproductoResponse

    fun onRegistronuevop(
        modelo: String,
        precio: String,
        talla: String,
        cantidad: String,
        zku: String,
        urlimagen: String,
        idcategoria: String,
    ){

        _modelo.value = modelo
        _precio.value = precio
        _talla.value = talla
        _cantidad.value = cantidad
        _zku.value = zku
        _urlimagen.value = urlimagen
        _idcategoria.value = idcategoria

    }

    fun limpiarCampos(){
        _modelo.value = ""
        _precio.value = ""
        _talla.value = ""
        _cantidad.value  = ""
        _zku.value = ""
        _urlimagen.value = ""
        _idcategoria.value = ""

    }
    fun registroproducto(){
        viewModelScope.launch {
            val response = nuevoproductoUsecase(
                NuevoProductoResquest(
                    modelo.value!!,
                    precio.value!!, talla.value!!, cantidad.value!!,
                    zku.value!!, urlimagen.value!!, idcategoria.value!!
                )
            )
            //Log.i("RESPONSE", response.toString())
            _nuevoproductoResponse.value = Evento(response)
        }
    }

}