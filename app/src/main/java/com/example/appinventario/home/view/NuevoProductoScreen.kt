package com.example.appinventario.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BlurOn
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ModeEditOutline
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.ProductionQuantityLimits
import androidx.compose.material.icons.filled.Tag
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
import com.example.appinventario.home.viewmodel.NuevoProductoViewModel
import kotlinx.coroutines.launch


@Composable
fun nuevoproductoScreen(
    nuevoproductoViewModel: NuevoProductoViewModel,
    navController: NavController
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Observa los valores del ViewModel
    val modelo by nuevoproductoViewModel.modelo.observeAsState("")
    val precio by nuevoproductoViewModel.precio.observeAsState("")
    val talla by nuevoproductoViewModel.talla.observeAsState("")
    val cantidad by nuevoproductoViewModel.cantidad.observeAsState("")
    val zku by nuevoproductoViewModel.zku.observeAsState("")
    val urlimagen by nuevoproductoViewModel.urlimagen.observeAsState("")
    val idcategoria by nuevoproductoViewModel.idcategoria.observeAsState("")

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            item {
                cabeceraNuevoproducto()
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }

            // Lista de los campos del formulario
            items(1) {
                txtmodelo(modelo) { nuevoproductoViewModel.onRegistronuevop(it, precio, talla, cantidad, zku, urlimagen, idcategoria) }
                Spacer(modifier = Modifier.height(16.dp))

                txtprecio(precio) { nuevoproductoViewModel.onRegistronuevop(modelo, it, talla, cantidad, zku, urlimagen, idcategoria) }
                Spacer(modifier = Modifier.height(16.dp))

                txttalla(talla) { nuevoproductoViewModel.onRegistronuevop(modelo, precio, it, cantidad, zku, urlimagen, idcategoria) }
                Spacer(modifier = Modifier.height(16.dp))

                txtcantidadd(cantidad) { nuevoproductoViewModel.onRegistronuevop(modelo, precio, talla, it, zku, urlimagen, idcategoria) }
                Spacer(modifier = Modifier.height(16.dp))

                txtzku(zku) { nuevoproductoViewModel.onRegistronuevop(modelo, precio, talla, cantidad, it, urlimagen, idcategoria) }
                Spacer(modifier = Modifier.height(16.dp))

                txturlimagen(urlimagen) { nuevoproductoViewModel.onRegistronuevop(modelo, precio, talla, cantidad, zku, it, idcategoria) }
                Spacer(modifier = Modifier.height(16.dp))

                txtidcategoria(idcategoria) { nuevoproductoViewModel.onRegistronuevop(modelo, precio, talla, cantidad, zku, urlimagen, it ) }
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Button(
                    onClick = {
                        nuevoproductoViewModel.registroproducto()
                        scope.launch {
                            snackbarHostState.showSnackbar("Producto registrado exitosamente", duration = SnackbarDuration.Short)
                        }
                        nuevoproductoViewModel.limpiarCampos()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Registrar Producto")
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun cabeceraNuevoproducto() {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            imageVector = Icons.Filled.ProductionQuantityLimits, contentDescription = "logo",
            Modifier
                .width(100.dp)
                .height(100.dp)
        )
        Text(text = "INGRESO NUEVO PRODUCTOS", fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}




@Composable
fun txtmodelo(modelo: String, onTextChanged: (String) -> Unit){
    OutlinedTextField(value = modelo,
        onValueChange = {onTextChanged(it)},
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Modelo") },
        leadingIcon = { Icon(imageVector = Icons.Default.ModeEditOutline, contentDescription = "persona") },
        maxLines = 1,
        singleLine = true
    )
}
@Composable
fun txtprecio(precio: String, onTextChanged: (String) -> Unit){
    OutlinedTextField(value = precio,
        onValueChange = {onTextChanged(it)},
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Precio") },
        leadingIcon = { Icon(imageVector = Icons.Default.MoneyOff, contentDescription = "persona") },
        maxLines = 1,
        singleLine = true
    )
}
@Composable
fun txttalla(talla: String, onTextChanged: (String) -> Unit){
    OutlinedTextField(value = talla,
        onValueChange = {onTextChanged(it)},
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Talla") },
        leadingIcon = { Icon(imageVector = Icons.Default.Tag, contentDescription = "persona") },
        maxLines = 1,
        singleLine = true
    )
}

@Composable
fun txtcantidadd(cantidad: String, onTextChanged: (String) -> Unit){
    OutlinedTextField(value = cantidad,
        onValueChange = {onTextChanged(it)},
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Cantidad") },
        leadingIcon = { Icon(imageVector = Icons.Default.Numbers, contentDescription = "persona") },
        maxLines = 1,
        singleLine = true
    )
}

@Composable
fun txtzku(zku: String, onTextChanged: (String) -> Unit){
    OutlinedTextField(value = zku,
        onValueChange = {onTextChanged(it)},
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Zku") },
        leadingIcon = { Icon(imageVector = Icons.Default.Code, contentDescription = "persona") },
        maxLines = 1,
        singleLine = true
    )
}

@Composable
fun txturlimagen(urlimagen: String, onTextChanged: (String) -> Unit){
    OutlinedTextField(value = urlimagen,
        onValueChange = {onTextChanged(it)},
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "urlimagen") },
        leadingIcon = { Icon(imageVector = Icons.Default.BlurOn, contentDescription = "persona") },
        maxLines = 1,
        singleLine = true
    )
}
@Composable
fun txtidcategoria(idcategoria: String, onTextChanged: (String) -> Unit){
    OutlinedTextField(value = idcategoria,
        onValueChange = {onTextChanged(it)},
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Codigo Categoria") },
        leadingIcon = { Icon(imageVector = Icons.Default.Category, contentDescription = "persona") },
        maxLines = 1,
        singleLine = true
    )
}
