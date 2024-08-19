package com.example.appinventario.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appinventario.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun menuScreen(navController: NavController // Agregar NavController como parámetro
) {
    val images = listOf(
        R.drawable.fonfo,
        R.drawable.ima,
        R.drawable.fonfo // Reemplaza con tus recursos de imagen
    )
    var currentImageIndex by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = currentImageIndex) {
        coroutineScope.launch {
            delay(2000) // Cambiar la imagen cada 2 segundos
            currentImageIndex = (currentImageIndex + 1) % images.size
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Carrusel de imágenes
        Image(
            painter = painterResource(id = images[currentImageIndex]),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Menú de iconos
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MenuItem(icon = R.drawable.ic_launcher_background, title = "Restaurante")
            MenuItem(icon = R.drawable.ic_launcher_foreground, title = "Supermercado")
            MenuItem(icon = R.drawable.ic_launcher_foreground, title = "RappiFavor")
            // Agrega más ítems según sea necesario
        }

        // Segunda fila de menú (si es necesario)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MenuItem(icon = R.drawable.ic_launcher_foreground, title = "Farmacia")
            MenuItem(icon = R.drawable.ic_launcher_foreground, title = "Express")
            MenuItem(icon = R.drawable.ic_launcher_foreground, title = "RappiMall")
            // Agrega más ítems según sea necesario
        }
    }
}

@Composable
fun MenuItem(icon: Int, title: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { /* Acción cuando se hace clic en el ítem */ }
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .background(Color.LightGray, RoundedCornerShape(8.dp))
                .padding(16.dp)
        )
        Text(text = title, fontSize = 12.sp)
    }
}
