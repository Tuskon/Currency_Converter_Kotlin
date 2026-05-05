package com.example.currencyconverter.ui.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import com.example.currencyconverter.ui.screens.ConveterScreen
import com.example.currencyconverter.ui.screens.IntialScreen
import androidx.navigation.compose.composable


@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "initial") {
        composable("initial") { IntialScreen(navController) }
        composable("converter") { ConveterScreen(navController) }
    }
}