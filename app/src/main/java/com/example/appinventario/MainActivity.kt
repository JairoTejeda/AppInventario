package com.example.appinventario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appinventario.auth.view.loginScreen
import com.example.appinventario.auth.view.registroScreen
import com.example.appinventario.home.viewmodel.IngresoViewModel
import com.example.appinventario.auth.viewmodel.LoginViewModel
import com.example.appinventario.auth.viewmodel.RegistroViewModel
import com.example.appinventario.core.ruta.Rutainventario
import com.example.appinventario.home.view.homeScreen
import com.example.appinventario.home.viewmodel.CategoriaViewModel
import com.example.appinventario.home.viewmodel.NuevoProductoViewModel
import com.example.appinventario.home.viewmodel.ProductoViewModel
import com.example.appinventario.home.viewmodel.SalidaViewModel
import com.example.appinventario.ui.theme.AppInventarioTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()
    private  val registroViewModel:RegistroViewModel by viewModels()
    private  val productoViewModel:ProductoViewModel by viewModels()
    private  val ingresoViewModel: IngresoViewModel by viewModels()
    private  val salidaViewModel: SalidaViewModel by viewModels()
    private  val categoriaViewModel: CategoriaViewModel by viewModels()
    private  val nuevoproductoViewModel: NuevoProductoViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppInventarioTheme {
                val navigation = rememberNavController()
                NavHost(navController = navigation,
                    startDestination = Rutainventario.homeScreen.path,
                    builder ={
                        composable(Rutainventario.loginScreen.path){
                            loginScreen(loginViewModel, navigation)

                        }
                        composable(Rutainventario.registroScreen.path){
                            registroScreen(registroViewModel,navigation)
                        }

                        composable(Rutainventario.homeScreen.path) {
                            homeScreen(productoViewModel, ingresoViewModel,salidaViewModel,
                                categoriaViewModel,nuevoproductoViewModel)
                        }

                    })
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppInventarioTheme {

    }
}