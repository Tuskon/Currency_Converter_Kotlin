package com.example.currencyconverter.ui.screens.converter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.currencyconverter.ui.theme.Warning


@Composable
fun ConverterScreen(navController: NavController, viewModel: ConverterViewModel = viewModel()) {

    val state = viewModel.state

    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                viewModel.goBack(navController)
            },
            Modifier.width(200.dp)
        ) {
            Text(text = "Ir para tela de início")

        }

        Button(
            onClick = {
                viewModel.loadCountries()
            },
            Modifier.width(200.dp)
        ) {
            Text(text = "Obter localidades")

        }
        if (state.alreadyMakeRequest) {
            if (state.isLoading) {
                CircularProgressIndicator(Modifier.padding(vertical = 30.dp))
            } else if (state.error == null) {
                if (!state.data.isNullOrEmpty() && state.data.size > 0) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(vertical = 30.dp)
                            .width(200.dp)
                            .height(400.dp)

                    ) {

                        items(state.data) { country ->
                            Text(
                                text = country.name.common,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Start
                            )
                        }
                    }
                } else {
                    Text(
                        text = "Lista de Itens Vazia",
                        color = Warning,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 30.dp)
                    )
                }
            } else {
                Text(
                    text = "Erro: ${state.error}",
                    color = Color.Red,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 30.dp)
                )
            }
        }
    }

}