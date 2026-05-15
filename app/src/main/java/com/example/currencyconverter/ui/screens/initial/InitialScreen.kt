package com.example.currencyconverter.ui.screens.initial


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.currencyconverter.ui.theme.TextTitleColorBlue
import com.example.currencyconverter.ui.theme.TextTitleColorGray
import com.example.currencyconverter.R
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource

@Composable
fun InitialScreen(navController: NavController, viewModel: InitialViewModel = viewModel()) {

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    Column(
        Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFD7E3FC),
                        Color(0xFFF4F5F7)
                    )
                )
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            Modifier
                .height(screenHeight * 0.3f)
                .padding(top = screenHeight * 0.2f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Welcome to ConvY",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = TextTitleColorBlue
            )

            Text(
                text = """"Simple is enough"""",
                fontSize = 18.sp,
                fontWeight = FontWeight.W400,
                color = TextTitleColorGray
            )

        }

        Box(
            Modifier
                .width(350.dp)
                .height(350.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.convy),
                contentDescription = "Descrição para acessibilidade",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Button(
            onClick = {
                viewModel.goToConverterScreen(navController)
            },
            Modifier
                .width(250.dp)
                .padding(top = 30.dp)
        ) {
            Text(text = "Start Converter")
        }

    }
}