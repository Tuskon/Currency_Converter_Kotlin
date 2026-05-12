package com.example.currencyconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.currencyconverter.ui.navigation.MyApp
import com.example.currencyconverter.ui.screens.converter.ConverterScreen
import com.example.currencyconverter.ui.screens.initial.InitialScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp()
        }
    }
}

@Preview
@Composable
private fun PreviewScreen() {
    val navController = rememberNavController()
    ConverterScreen(navController)
}
