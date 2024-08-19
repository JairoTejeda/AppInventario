package com.example.appinventario.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appinventario.auth.data.network.request.LoginRequest
import com.example.appinventario.auth.data.network.response.LoginResponse
import com.example.appinventario.auth.domain.LoginUseCase
import com.example.appinventario.core.util.Evento
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase) : ViewModel() {

    private  val _usuariousu = MutableLiveData<String>()
    val  usuariousu: LiveData<String> = _usuariousu

    private  val _password = MutableLiveData<String>()
    val  password: LiveData<String> = _password

    private var _botonLoginHabilitado = MutableLiveData<Boolean>()
    var botonLoginHabilitado :LiveData<Boolean> = _botonLoginHabilitado
    private val _loginResponse = MutableLiveData<Evento<LoginResponse>>()
    val loginResponse: LiveData<Evento<LoginResponse>> = _loginResponse

    fun onValueChanged(usuariousu: String, password: String){
        _usuariousu.value = usuariousu
        _password.value = password
        _botonLoginHabilitado.value = habilitarBotonLogin(usuariousu, password)

    }

    fun loginUsuarioPassword(){
        viewModelScope.launch {
            val response = loginUseCase(
                LoginRequest(usuariousu.value!!, password.value!!)
            )
            _loginResponse.value = Evento(response)
        }
    }

    fun habilitarBotonLogin(usuariousu:String, password: String) = usuariousu.length > 2
            && password.length > 2


}