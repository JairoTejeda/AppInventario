package com.example.appinventario.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.MeetingRoom
import androidx.compose.material.icons.filled.Mode
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.ProductionQuantityLimits
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appinventario.home.viewmodel.IngresoViewModel
import com.example.appinventario.core.ruta.Rutainventario
import kotlinx.coroutines.launch

@Composable
fun ingresoScreen(
    ingresoViewModel: IngresoViewModel,
    navController: NavController
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Observa los valores del ViewModel
    val productoId by ingresoViewModel.productoId.observeAsState("")
    val cantidad by ingresoViewModel.cantidad.observeAsState("")

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .background(Color(0xFFF2F2F2)) // Fondo claro
                .verticalScroll(rememberScrollState()) // Permite el desplazamiento vertical
        ) {
            cabeceraIngreso()

            Spacer(modifier = Modifier.height(20.dp))

            // Formulario de ingreso
            txtid(productoId) { ingresoViewModel.onRegistro(it, cantidad) }
            Spacer(modifier = Modifier.height(16.dp))
            txtcantidad(cantidad) { ingresoViewModel.onRegistro(productoId, it) }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    ingresoViewModel.registroingresoproducto()
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            "Producto registrado exitosamente",
                            duration = SnackbarDuration.Short
                        )
                    }
                    ingresoViewModel.limpiarCampos()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3A6BEA)) // Color azul
            ) {
                Text(text = "Registrar Ingreso", color = Color.White)
            }
        }
    }
}

@Composable
fun cabeceraIngreso() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = Icons.Filled.ProductionQuantityLimits,
            contentDescription = "logo",
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .background(Color.White, shape = CircleShape) // Fondo blanco y forma circular
                .padding(8.dp) // Espaciado interno
        )
        Text(
            text = "INGRESO PRODUCTO",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3A6BEA) // Color azul
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun txtid(productoId: String, onTextChanged: (String) -> Unit) {
    OutlinedTextField(
        value = productoId,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White), // Fondo blanco
        label = { Text(text = "Código del Producto") },
        leadingIcon = { Icon(imageVector = Icons.Default.Code, contentDescription = "icon") },
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
fun txtcantidad(cantidad: String, onTextChanged: (String) -> Unit) {
    OutlinedTextField(
        value = cantidad,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White), // Fondo blanco
        label = { Text(text = "Cantidad") },
        leadingIcon = { Icon(imageVector = Icons.Default.Mode, contentDescription = "icon") },
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF3A6BEA),
            cursorColor = Color(0xFF3A6BEA)
        )
    )
}