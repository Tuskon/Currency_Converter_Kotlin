package com.example.currencyconverter.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import com.example.currencyconverter.ui.screens.converter.ConverterScreen
import com.example.currencyconverter.ui.screens.initial.InitialScreen
import androidx.navigation.compose.composable


@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "initial") {
        composable("initial") { InitialScreen(navController) }
        composable("converter") { ConverterScreen(navController) }
    }
}