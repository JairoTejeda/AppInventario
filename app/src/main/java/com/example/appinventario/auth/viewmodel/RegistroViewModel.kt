package com.example.appinventario.auth.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appinventario.auth.data.network.request.RegistroRequest
import com.example.appinventario.auth.data.network.response.RegistroResponse
import com.example.appinventario.auth.domain.RegistroUseCase
import com.example.appinventario.core.util.Evento
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistroViewModel @Inject constructor(
    private val registroUseCase: RegistroUseCase) : ViewModel() {

    private val _nombre = MutableLiveData<String>()
    val nombre : LiveData<String> = _nombre
    private val _emailusu = MutableLiveData<String>()
    val emailusu : LiveData<String> = _emailusu
    private val _celularusu= MutableLiveData<String>()
    val celularusu : LiveData<String> = _celularusu
    private val _usuariousu= MutableLiveData<String>()
    val usuariousu : LiveData<String> = _usuariousu
    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password
    private val _rolusu = MutableLiveData<String>()
    val rolusu : LiveData<String> = _rolusu

    private val _registroResponse = MutableLiveData<Evento<RegistroResponse>>()
    val registroResponse: LiveData<Evento<RegistroResponse>> = _registroResponse

    fun onRegistroChanged(
        nombre: String,
        emailusu: String,
        celularusu:String,
        usuariousu:String,
        password: String,
        rolusu: String
    ){
        _nombre.value = nombre
        _emailusu.value = emailusu
        _celularusu.value = celularusu
        _usuariousu.value = usuariousu
        _password.value = password
        _rolusu.value = rolusu
    }

    fun setearFormularioRegistro(){
        _nombre.value = ""
        _emailusu.value = ""
        _celularusu.value = ""
        _usuariousu.value = ""
        _password.value = ""
        _rolusu.value = ""
    }
    fun registrarPersona(){
        viewModelScope.launch {
            val response = registroUseCase(
                RegistroRequest(
                    nombre.value!!,
                    emailusu.value!!, celularusu.value!!, usuariousu.value!!,
                    password.value!!, rolusu.value!!
                )
            )
            //Log.i("RESPONSE", response.toString())
            _registroResponse.value = Evento(response)
        }
    }
}