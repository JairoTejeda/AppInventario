package com.example.appinventario.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cached
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.MeetingRoom
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.ProductionQuantityLimits
import androidx.compose.material.icons.filled.SaveAlt
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appinventario.home.viewmodel.SalidaViewModel
import kotlinx.coroutines.launch

@Composable
fun salidaScreen(
    salidaViewModel: SalidaViewModel,
    navController: NavController // Agregar NavController como parámetro
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Observa los valores del ViewModel
    val productoId by salidaViewModel.productoId.observeAsState("")
    val cantidad by salidaViewModel.cantidad.observeAsState("")

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp)
        ) {
            cabeceraSalida()

            Spacer(modifier = Modifier.height(20.dp))

            // Formulario de salida
            txid(productoId) { salidaViewModel.onSRegistro(it, cantidad) }
            Spacer(modifier = Modifier.height(16.dp))
            txcantidad(cantidad) { salidaViewModel.onSRegistro(productoId, it) }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    salidaViewModel.registrosalidaproducto()
                    scope.launch {
                        snackbarHostState.showSnackbar("Producto registrado exitosamente", duration = SnackbarDuration.Short)
                    }
                    salidaViewModel.setearFormularioRegistro()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Registrar Salida")
            }
        }
    }
}

@Composable
fun cabeceraSalida() {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            imageVector = Icons.Filled.SaveAlt, contentDescription = "logo",
            Modifier
                .width(100.dp)
                .height(100.dp)
        )
        Text(text = "SALIDA PRODUCTO", fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun txid(productoId: String, onTextChanged: (String) -> Unit) {
    OutlinedTextField(
        value = productoId,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Código del Producto") },
        leadingIcon = { Icon(imageVector = Icons.Default.Code, contentDescription = "icon") },
        maxLines = 1,
        singleLine = true
    )
}

@Composable
fun txcantidad(cantidad: String, onTextChanged: (String) -> Unit) {
    OutlinedTextField(
        value = cantidad,
        onValueChange = { onTextChanged(it) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Cantidad") },
        leadingIcon = { Icon(imageVector = Icons.Default.Cached, contentDescription = "icon") },
        maxLines = 1,
        singleLine = true
    )
}