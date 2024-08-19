package com.example.appinventario.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.appinventario.R
import com.example.appinventario.home.data.network.response.ProductosResponse
import com.example.appinventario.home.viewmodel.ProductoViewModel


@Composable
fun productoScreen(productoViewModel: ProductoViewModel) {
    val productos by productoViewModel.productoResponse.observeAsState(emptyList())
    LazyColumn {
        items(productos) { producto ->
            productotaItem(producto = producto)
        }
    }
}

@Composable
fun productotaItem(producto: ProductosResponse) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), // Reducción de la elevación
        shape = MaterialTheme.shapes.medium, // Forma más sutil
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), // Aumento del padding para mejor separación
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = producto.urlimagen)
                        .apply {
                            crossfade(true)
                            placeholder(R.drawable.ima)
                        }.build()
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .padding(4.dp) // Padding adicional alrededor de la imagen
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "Modelo",
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray // Color más suave
                )
                Text(
                    text = producto.modelo,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 4.dp) // Espacio entre textos
                )
                Text(
                    text = "Precio",
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray
                )
                Text(
                    text = producto.precio,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Talla",
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray
                )
                Text(
                    text = producto.talla,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Cantidad",
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray
                )
                Text(
                    text = producto.cantidad,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Zku",
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray
                )
                Text(
                    text = producto.zku,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}