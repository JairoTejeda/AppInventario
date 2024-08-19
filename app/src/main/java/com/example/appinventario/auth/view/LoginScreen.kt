package com.example.appinventario.auth.view

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appinventario.R
import com.example.appinventario.auth.viewmodel.LoginViewModel
import com.example.appinventario.core.ruta.Rutainventario
import kotlinx.coroutines.launch

@Composable
fun loginScreen(loginViewModel: LoginViewModel,
                navController: NavController) {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background) // Usar el tema para el fondo
        ) {
            Header(modifier = Modifier.align(Alignment.TopEnd))
            LoginForm(
                modifier = Modifier.align(Alignment.Center),
                loginViewModel, snackbarHostState, navController
            )
            Footer(modifier = Modifier.align(Alignment.BottomCenter), navController)
        }
    }
}

@Composable
fun Header(modifier: Modifier) {
    val activity = LocalContext.current as Activity
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "Cerrar",
        tint = MaterialTheme.colorScheme.onBackground, // Usar el tema para el color de ícono
        modifier = modifier
            .padding(16.dp)
            .clickable { activity.finish() }
    )
}

@Composable
fun Footer(modifier: Modifier, navController: NavController) {
    Column(modifier = modifier.fillMaxWidth()) {
        HorizontalDivider(
            modifier = Modifier
                .background(Color(0xFFCCCCCC))
                .height(1.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(16.dp))
        RegisterLink(navController)
        Spacer(modifier = Modifier.size(16.dp))
    }
}

@Composable
fun RegisterLink(navController: NavController) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(text = "¿No tienes cuenta? ", fontSize = 14.sp, color = Color.Gray)
        Text(
            text = "Regístrate Aquí",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {
                navController.navigate(Rutainventario.registroScreen.path)
            }
        )
    }
}

@Composable
fun LoginForm(
    modifier: Modifier,
    loginViewModel: LoginViewModel,
    snackbarHostState: SnackbarHostState,
    navController: NavController
) {
    val usuario by loginViewModel.usuariousu.observeAsState("")
    val password by loginViewModel.password.observeAsState("")
    val isLoginButtonEnabled by loginViewModel.botonLoginHabilitado.observeAsState(false)
    Column(
        modifier = modifier
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        Logo(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        UserTextField(usuario) { loginViewModel.onValueChanged(it, password) }
        Spacer(modifier = Modifier.size(16.dp))
        PasswordTextField(password) { loginViewModel.onValueChanged(usuario, it) }
        Spacer(modifier = Modifier.size(24.dp))
        LoginButton(isLoginButtonEnabled, loginViewModel, snackbarHostState, navController)
    }
}

@Composable
fun Logo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.fonfo),
        contentDescription = "Logo",
        modifier = modifier
            .size(100.dp)
            .padding(8.dp)
    )
}

@Composable
fun LoginButton(
    isEnabled: Boolean,
    loginViewModel: LoginViewModel,
    snackbarHostState: SnackbarHostState,
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    val loginResponse by loginViewModel.loginResponse.observeAsState()

    Button(
        onClick = { loginViewModel.loginUsuarioPassword() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isEnabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
        )
    ) {
        Text(text = "Ingresar", color = MaterialTheme.colorScheme.onPrimary)
    }

    loginResponse?.obtenerContenidoSiCambio()?.let { response ->
        if (response.rpta) {
            navController.navigate(Rutainventario.homeScreen.path)
        } else {
            scope.launch {
                snackbarHostState.showSnackbar(
                    "Login Fallido: ${response.mensaje}",
                    actionLabel = "OK",
                    duration = SnackbarDuration.Long
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserTextField(usuario: String, onTextChanged: (String) -> Unit) {
    OutlinedTextField(
        value = usuario,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Usuario") },
        maxLines = 1,
        singleLine = true,
        shape = MaterialTheme.shapes.medium,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.primary
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(password: String, onTextChanged: (String) -> Unit) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Password") },
        maxLines = 1,
        singleLine = true,
        shape = MaterialTheme.shapes.medium,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (isPasswordVisible) {
                Icons.Filled.VisibilityOff
            } else {
                Icons.Filled.Visibility
            }
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(imageVector = image, contentDescription = if (isPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña")
            }
        },
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.primary
        )
    )
}