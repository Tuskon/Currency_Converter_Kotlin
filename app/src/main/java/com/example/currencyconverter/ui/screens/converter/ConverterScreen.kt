package com.example.currencyconverter.ui.screens.converter

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.graphics.Brush
import com.example.currencyconverter.ui.theme.TextCountryColorBlue
import com.example.currencyconverter.ui.theme.TextTitleColorBlue
import com.example.currencyconverter.ui.theme.TextTitleColorGray
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.platform.LocalConfiguration
import com.example.currencyconverter.ui.theme.IconColorChevronGray
import androidx.compose.ui.text.TextStyle
import com.example.currencyconverter.ui.theme.TitleInputColorGray
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.currencyconverter.ui.components.countryBottomSheet.CountryBottomSheet
import com.example.currencyconverter.ui.components.modalFailRequest.ModalFailRequestCurrency
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding

@SuppressLint("DefaultLocale")
@Composable
fun ConverterScreen(navController: NavController, viewModel: ConverterViewModel = viewModel()) {

    val state = viewModel.state
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
            )
            .verticalScroll(rememberScrollState())
            .imePadding()
            .navigationBarsPadding()
            .padding(bottom = 80.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            Modifier
                .height(screenHeight * 0.3f)
                .padding(top = screenHeight * 0.2f),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Currency Converter",
                color = TextTitleColorBlue,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                modifier = Modifier.width(350.dp),
                text = "Check live rates, set rate alerts, receive notifications and more.",
                textAlign = TextAlign.Center,
                color = TextTitleColorGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
            )
        }

        Column(
            Modifier
                .height(290.dp)
                .width(320.dp)
                .padding(top = 20.dp)
                .background(color = Color.White, RoundedCornerShape(25.dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                Modifier
                    .width(280.dp)
                    .padding(bottom = 20.dp)
            ) {
                if (state.firstCountryDto == null) {
                    Text(
                        modifier = Modifier.clickable(
                            enabled = true,
                            onClick = { viewModel.optionFirstBottomSheet(true) }),
                        text = "Select First Country",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextCountryColorBlue

                    )
                } else {
                    Text(
                        modifier = Modifier.padding(bottom = 6.dp),
                        color = TitleInputColorGray,
                        text = "Amount"
                    )
                    Row(
                        Modifier
                            .width(280.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            Modifier
                                .width(120.dp)
                                .clickable(
                                    enabled = true,
                                    onClick = { viewModel.optionFirstBottomSheet(true) }),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            AsyncImage(
                                model = state.firstCountryDto.png,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,

                                modifier = Modifier
                                    .size(38.dp)
                                    .clip(CircleShape)
                            )
                            Row {
                                Text(
                                    text = "${state.firstCountryDto.currency}",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextCountryColorBlue

                                )

                                Icon(
                                    modifier = Modifier.size(22.dp),
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = "Chevron Down",
                                    tint = IconColorChevronGray
                                )
                            }
                        }
                        if (!state.isLoadingSecondCountry) {
                            BasicTextField(
                                modifier = Modifier
                                    .width(140.dp)
                                    .height(45.dp)
                                    .background(Color(0xFFEFEFEF), RoundedCornerShape(10.dp))
                                    .padding(horizontal = 10.dp),

                                value = state.valueFirstCountry ?: "",

                                onValueChange = {
                                    viewModel.updateFirstValue(it)
                                },
                                enabled = !state.isLoadingSecondCountry,
                                singleLine = true,

                                textStyle = TextStyle(
                                    fontSize = 15.sp,
                                    color = Color.Black
                                ),

                                decorationBox = { innerTextField ->

                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.CenterStart
                                    ) {

                                        if (state.valueFirstCountry.isNullOrEmpty()) {
                                            Text(
                                                text = "0.00",
                                                fontSize = 15.sp,
                                                color = Color.Gray
                                            )
                                        }

                                        innerTextField()
                                    }
                                }
                            )
                        } else {
                            CircularProgressIndicator(Modifier.size(size = 30.dp))
                        }
                    }
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {

                Box(
                    Modifier
                        .background(Color(0xFFE7E7EE))
                        .width(120.dp)
                        .height(1.dp)
                )
                Box(
                    Modifier
                        .background(color = TextCountryColorBlue, RoundedCornerShape(100.dp))
                        .width(40.dp)
                        .height(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(26.dp),
                        imageVector = Icons.Default.SwapVert,
                        contentDescription = "Chevron Down",
                        tint = Color.White
                    )
                }
                Box(
                    Modifier
                        .background(Color(0xFFE7E7EE))
                        .width(120.dp)
                        .height(1.dp)
                )

            }
            Column(
                Modifier
                    .width(280.dp)
                    .padding(top = 20.dp)
            ) {
                if (state.secondCountryDto === null) {
                    Text(
                        modifier = Modifier.clickable(
                            enabled = true,
                            onClick = { viewModel.optionSecondBottomSheet(true) }),
                        text = "Select Second Country",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextCountryColorBlue

                    )
                } else {
                    Text(
                        modifier = Modifier.padding(bottom = 6.dp),
                        color = TitleInputColorGray,
                        text = "Converted Amount"
                    )
                    Row(
                        Modifier
                            .width(280.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            Modifier
                                .width(120.dp)
                                .clickable(
                                    enabled = true,
                                    onClick = { viewModel.optionSecondBottomSheet(true) }),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            AsyncImage(
                                model = state.secondCountryDto.png,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,

                                modifier = Modifier
                                    .size(38.dp)
                                    .clip(CircleShape)
                            )
                            Row {
                                Text(
                                    text = "${state.secondCountryDto.currency}",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextCountryColorBlue

                                )

                                Icon(
                                    modifier = Modifier.size(22.dp),
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = "Chevron Down",
                                    tint = IconColorChevronGray
                                )
                            }
                        }
                        if (!state.isLoadingFirstCountry) {
                            BasicTextField(
                                modifier = Modifier
                                    .width(140.dp)
                                    .height(45.dp)
                                    .background(Color(0xFFEFEFEF), RoundedCornerShape(10.dp))
                                    .padding(horizontal = 10.dp),

                                value = state.valueSecondCountry ?: "",

                                onValueChange = {
                                    viewModel.updateSecondValue(it)
                                },
                                enabled = !state.isLoadingFirstCountry,
                                singleLine = true,

                                textStyle = TextStyle(
                                    fontSize = 15.sp,
                                    color = Color.Black
                                ),

                                decorationBox = { innerTextField ->

                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.CenterStart
                                    ) {

                                        if (state.valueSecondCountry.isNullOrEmpty()) {
                                            Text(
                                                text = "0.00",
                                                fontSize = 15.sp,
                                                color = Color.Gray
                                            )
                                        }

                                        innerTextField()
                                    }
                                }
                            )
                        } else {
                            CircularProgressIndicator(Modifier.size(size = 30.dp))
                        }
                    }
                }
            }
        }
        if (state.firstCountryDto !== null && state.secondCountryDto !== null) {
            Column(
                Modifier
                    .width(300.dp)
                    .padding(top = 20.dp)
            ) {
                Text(
                    text = "Indicative Exchange Rate",
                    fontSize = 15.sp,
                    color = Color.Gray
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (state.isLoadingFirstCountry === false) {
                        Text(
                            modifier = Modifier.padding(top = 10.dp),
                            text = "${
                                if (state.valueFirstCountry.isNullOrEmpty()) "0.00"
                                else state.valueFirstCountry
                            } ${state.firstCountryDto.currency}",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    } else {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(15.dp)
                                .padding(top = 6.dp, end = 3.dp),
                            strokeWidth = 2.dp
                        )
                    }
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        text = " = ",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    if (state.isLoadingSecondCountry === false) {
                        Text(
                            modifier = Modifier.padding(top = 10.dp),
                            text = "${
                                if (state.valueSecondCountry.isNullOrEmpty()) "0.00"
                                else state.valueSecondCountry
                            } ${state.secondCountryDto.currency}",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    } else {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(15.dp)
                                .padding(top = 6.dp, start = 3.dp),
                            strokeWidth = 2.dp
                        )
                    }
                }

            }
        }

        if (state.showFirstBottomSheet || state.showSecondBottomSheet) {

            CountryBottomSheet(
                onDismiss = {
                    viewModel.optionSecondBottomSheet(false)
                    viewModel.optionFirstBottomSheet(false)
                },
                onCountrySelected = { country ->
                    if (state.showSecondBottomSheet) {
                        viewModel.updateSecondCountryDto(country)
                    }
                    if (state.showFirstBottomSheet) {
                        viewModel.updateFirstCountryDto(country)
                    }
                    viewModel.optionSecondBottomSheet(false)
                    viewModel.optionFirstBottomSheet(false)
                },
                onErrorState={ viewModel.goBackToInitialScreen(navController,choice = false)}
            )
        }
        if (state.failCurrencyRequest) {
            ModalFailRequestCurrency(
                onConfirm = {
                    viewModel.madeCurrencyRequestAgain()
                },
                onDismiss = {
                    viewModel.goBackToInitialScreen(navController,choice = true)
                }
            )
        }
    }
}