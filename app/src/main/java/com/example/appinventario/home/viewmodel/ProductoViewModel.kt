package com.example.appinventario.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appinventario.home.data.network.response.ProductosResponse
import com.example.appinventario.home.domain.ProductoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductoViewModel @Inject constructor(
    private val productoUseCase: ProductoUseCase,
) : ViewModel() {

    private val _productoResponse = MutableLiveData<List<ProductosResponse>>()
    val productoResponse: LiveData<List<ProductosResponse>> = _productoResponse

    init {
        listarProductos()
    }
    fun listarProductos(){
        viewModelScope.launch {
            val response  = productoUseCase()
            _productoResponse.value = response
        }
    }

}