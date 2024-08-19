package com.example.appinventario.auth.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Person4
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appinventario.auth.viewmodel.RegistroViewModel
import com.example.appinventario.core.ruta.Rutainventario
import kotlinx.coroutines.launch

@Composable
fun registroScreen(registroViewModel: RegistroViewModel, navController: NavController) {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF2F2F2)) // Fondo claro
        ) {
            cabeceraRegistro()
            formularioRegistro(registroViewModel, snackbarHostState, navController)
        }
    }
}

@Composable
fun cabeceraRegistro() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = Icons.Filled.PersonPin,
            contentDescription = "logo",
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
        )
        Text(
            text = "REGÍSTRATE",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3A6BEA) // Color azul
        )
    }
}

@Composable
fun formularioRegistro(
    registroViewModel: RegistroViewModel,
    hostState: SnackbarHostState,
    navController: NavController
) {
    val nombre: String by registroViewModel.nombre.observeAsState(initial = "")
    val emailusu: String by registroViewModel.emailusu.observeAsState(initial = "")
    val celularusu: String by registroViewModel.celularusu.observeAsState(initial = "")
    val usuariousu: String by registroViewModel.usuariousu.observeAsState(initial = "")
    val password: String by registroViewModel.password.observeAsState(initial = "")
    val rolusu: String by registroViewModel.rolusu.observeAsState(initial = "")

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        txtnombre(nombre) { registroViewModel.onRegistroChanged(it, emailusu, celularusu, usuariousu, password, rolusu) }
        Spacer(modifier = Modifier.height(16.dp))
        txtemailusu(emailusu) { registroViewModel.onRegistroChanged(nombre, it, celularusu, usuariousu, password, rolusu) }
        Spacer(modifier = Modifier.height(16.dp))
        txcelularusu(celularusu) { registroViewModel.onRegistroChanged(nombre, emailusu, it, usuariousu, password, rolusu) }
        Spacer(modifier = Modifier.height(16.dp))
        txtusuariousu(usuariousu) { registroViewModel.onRegistroChanged(nombre, emailusu, celularusu, it, password, rolusu) }
        Spacer(modifier = Modifier.height(16.dp))
        txturolusu(rolusu) { registroViewModel.onRegistroChanged(nombre, emailusu, celularusu, usuariousu, password, it) }
        Spacer(modifier = Modifier.height(16.dp))
        txtpasswordreg(password) { registroViewModel.onRegistroChanged(nombre, emailusu, celularusu, usuariousu, it, rolusu) }
        Spacer(modifier = Modifier.height(24.dp))
        buttonregistro(registroViewModel, hostState)
        Spacer(modifier = Modifier.height(16.dp))
        buttonirlogin(navController)
    }
}

@Composable
fun buttonregistro(
    registroViewModel: RegistroViewModel,
    hostState: SnackbarHostState
) {
    val registroResponse by registroViewModel.registroResponse.observeAsState()
    val scope = rememberCoroutineScope()
    Button(
        onClick = { registroViewModel.registrarPersona() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3A6BEA))
    ) {
        Text(text = "Registrar Persona", color = Color.White)
    }
    registroResponse?.obtenerContenidoSiCambio()?.let { response ->
        scope.launch {
            hostState.showSnackbar(
                response.mensaje,
                actionLabel = "OK",
                duration = SnackbarDuration.Short
            )
        }
        registroViewModel.setearFormularioRegistro()
    }
}

@Composable
fun buttonirlogin(navController: NavController) {
    Button(
        onClick = { navController.navigate(Rutainventario.loginScreen.path) },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3A6BEA))
    ) {
        Text(text = "Regresar a Login", color = Color.White)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun txtnombre(nombre: String, onTextChanged: (String) -> Unit) {
    OutlinedTextField(
        value = nombre,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Nombre") },
        leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "persona") },
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF3A6BEA),
            cursorColor = Color(0xFF3A6BEA)
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun txtemailusu(emailusu: String, onTextChanged: (String) -> Unit) {
    OutlinedTextField(
        value = emailusu,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Correo") },
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "correo") },
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF3A6BEA),
            cursorColor = Color(0xFF3A6BEA)
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun txcelularusu(celularusu: String, onTextChanged: (String) -> Unit) {
    OutlinedTextField(
        value = celularusu,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Celular") },
        leadingIcon = { Icon(imageVector = Icons.Default.PhoneAndroid, contentDescription = "Celular") },
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF3A6BEA),
            cursorColor = Color(0xFF3A6BEA)
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun txtusuariousu(usuariousu: String, onTextChanged: (String) -> Unit) {
    OutlinedTextField(
        value = usuariousu,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Usuario") },
        leadingIcon = { Icon(imageVector = Icons.Default.Psychology, contentDescription = "Usuario") },
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF3A6BEA),
            cursorColor = Color(0xFF3A6BEA)
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun txtpasswordreg(password: String, onTextChanged: (String) -> Unit) {
    var visible by rememberSaveable { mutableStateOf(false) }
    OutlinedTextField(
        value = password,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Password") },
        leadingIcon = { Icon(imageVector = Icons.Default.Key, contentDescription = "password") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val icon = if (visible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
            IconButton(onClick = { visible = !visible }) {
                Icon(imageVector = icon, contentDescription = "Show/Hide Password")
            }
        },
        visualTransformation = if (visible) VisualTransformation.None else PasswordVisualTransformation(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF3A6BEA),
            cursorColor = Color(0xFF3A6BEA)
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun txturolusu(rolusu: String, onTextChanged: (String) -> Unit) {
    OutlinedTextField(
        value = rolusu,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "ROL") },
        leadingIcon = { Icon(imageVector = Icons.Default.PersonPin, contentDescription = "ROL") },
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF3A6BEA),
            cursorColor = Color(0xFF3A6BEA)
        )
    )
}
