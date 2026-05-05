package com.example.currencyconverter.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.currencyconverter.data.repository.ListCountryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun ConveterScreen(navController: NavController) {
    fun loadCountrys() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = ListCountryRepository()
                val countrys = repository.getCountries()

                Log.d("API", countrys.toString())
            } catch (e: Exception) {
                Log.e("API", "Erro: ${e.message}")
            }
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                navController.popBackStack()
            },
            Modifier.width(200.dp)
        ) {
            Text(text = "Ir para tela de início")

        }

        Button(
            onClick = {
                loadCountrys()
            },
            Modifier.width(200.dp)
        ) {
            Text(text = "Obter localidades")

        }
    }

}