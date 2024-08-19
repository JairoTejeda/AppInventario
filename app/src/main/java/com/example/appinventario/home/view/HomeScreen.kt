package com.example.appinventario.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.DensityMedium
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material.icons.filled.ProductionQuantityLimits
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.SubdirectoryArrowLeft
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appinventario.R
import com.example.appinventario.home.viewmodel.IngresoViewModel
import com.example.appinventario.core.ruta.Rutainventario
import com.example.appinventario.core.util.MenuItem
import com.example.appinventario.home.viewmodel.CategoriaViewModel
import com.example.appinventario.home.viewmodel.NuevoProductoViewModel
import com.example.appinventario.home.viewmodel.ProductoViewModel
import com.example.appinventario.home.viewmodel.SalidaViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun homeScreen(
    productoViewModel: ProductoViewModel,
    ingresoViewModel: IngresoViewModel,
    salidaViewModel: SalidaViewModel,
    categoriaViewModel: CategoriaViewModel,
    nuevoproductoViewModel: NuevoProductoViewModel
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                userName = "Jairo Tejeda",
                profileClick = {
                    // Acción cuando se hace clic en "Mi perfil"
                },
                menuItems = opcionesMenu(),
                onItemClick = { item ->
                    coroutineScope.launch {
                        drawerState.close() // Cierra el Drawer primero
                    }.invokeOnCompletion {
                        // Navegar solo después de cerrar el Drawer
                        navController.navigate(
                            when (item.titulo) {
                                "Productos" -> Rutainventario.productoScreen.path
                                "Ingreso Producto" -> Rutainventario.ingresoScreen.path
                                "Salida Producto" -> Rutainventario.salidaScreen.path
                                "Categorías" -> Rutainventario.categoriaScreen.path
                                "Agregar Productos" -> Rutainventario.nuevoproductoScreen.path
                                "Menu" -> Rutainventario.menuScreen.path
                                else -> Rutainventario.productoScreen.path // Ruta por defecto
                            }
                        )
                    }
                }
            )
        },
        content = {
            Column(modifier = Modifier.fillMaxSize()) {
                TopAppBar(
                    title = { Text(text = "Motos Pegaso") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Yellow,
                        titleContentColor = Color.Black
                    ),
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menú")
                        }
                    }
                )
                NavHost(
                    navController = navController,
                    startDestination = Rutainventario.productoScreen.path
                ) {
                    composable(Rutainventario.productoScreen.path) {
                        productoScreen(productoViewModel)
                    }
                    composable(Rutainventario.ingresoScreen.path) {
                        ingresoScreen(ingresoViewModel, navController)
                    }
                    composable(Rutainventario.salidaScreen.path) {
                        salidaScreen(salidaViewModel, navController)
                    }
                    composable(Rutainventario.categoriaScreen.path) {
                        categoriasScreen(categoriaViewModel, navController)
                    }
                    composable(Rutainventario.nuevoproductoScreen.path) {
                        nuevoproductoScreen(nuevoproductoViewModel, navController)
                    }
                }
            }
        }
    )
}


@Composable
fun DrawerContent(
    userName: String,
    profileClick: () -> Unit,
    menuItems: List<MenuItem>,
    onItemClick: (MenuItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize() // Asegura que la columna ocupe todo el tamaño disponible
            .background(Color.White) // Establecer un fondo blanco para toda la pantalla
    ) {
        // Header Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)
                .padding(20.dp)
        ) {
            Column {
                // User Profile Section
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.images),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(25.dp))
                    Column {
                        Text(text = userName, fontWeight = FontWeight.Bold)
                        Text(
                            text = "Mi perfil >",
                            color = Color.Gray,
                            modifier = Modifier.clickable { profileClick() }
                        )
                    }
                }
            }
        }
        Divider()

        // Menu Items
        LazyColumn(
            modifier = Modifier
                .fillMaxSize() // Permite que LazyColumn ocupe todo el espacio disponible
                .padding(vertical = 1.dp) // Ajustar padding para evitar corte del contenido
        ) {
            items(menuItems) { item ->
                DrawerMenuItem(item = item, onItemClick = onItemClick)
            }
        }
    }
}


@Composable
fun DrawerMenuItem(item: MenuItem, onItemClick: (MenuItem) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(item) }
            .background(Color.White) // Establecer el fondo blanco
            .padding(horizontal = 10.dp, vertical = 12.dp), // Ajustar el padding
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = null,
            tint = Color.Black, // Cambiar el color del ícono a negro
            modifier = Modifier.size(24.dp) // Establecer el tamaño del ícono
        )
        Spacer(modifier = Modifier.width(8.dp)) // Reducir el espaciado entre el ícono y el texto
        Text(
            text = item.titulo,
            color = Color.Black, // Cambiar el color del texto a negro
            fontSize = 14.sp // Ajustar el tamaño del texto
        )
    }
}

fun opcionesMenu(): List<MenuItem> {
    return listOf(
        MenuItem(Icons.Default.DensityMedium, "Menu"),
        MenuItem(Icons.Default.ProductionQuantityLimits, "Productos"),
        MenuItem(Icons.Default.Category, "categorias"),
        MenuItem(Icons.Default.ProductionQuantityLimits, "Ingreso Producto"),
        MenuItem(Icons.Default.Stop, "Salida Producto"),
        MenuItem(Icons.Default.SubdirectoryArrowLeft, "Agregar Productos"),

    )
}
