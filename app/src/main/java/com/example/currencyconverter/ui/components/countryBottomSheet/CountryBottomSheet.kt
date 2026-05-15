package com.example.currencyconverter.ui.components.countryBottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.RotateLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryBottomSheet(
    onDismiss: () -> Unit,
    onCountrySelected: (Country) -> Unit,
    onErrorState:() -> Unit,
    viewModel: CountryBottomSheetViewModel = viewModel()
) {

    val state = viewModel.state

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        onDismissRequest = {
            onDismiss()
        },

        sheetState = sheetState,

        dragHandle = null,

        containerColor = Color.White
    ) {


        if (state.isLoading) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(750.dp),

                contentAlignment = Alignment.Center
            ) {

                CircularProgressIndicator()
            }

        } else {

            if(state.errorState){
                Column(
                    Modifier
                        .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                        .fillMaxWidth()
                        .height(height = 240.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ){
                        Icon(
                            modifier = Modifier
                                .padding(end = 15.dp)
                                .size(22.dp)
                                .clickable(enabled = true, onClick = {onErrorState()}),
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.Black
                        )
                    }
                    Icon(
                        modifier = Modifier.size(62.dp),
                        imageVector = Icons.Default.ErrorOutline,
                        contentDescription = "Error Outline",
                        tint = Color.Red
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 25.dp),
                        text = "Failed to retrieve the country list",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )

                    Text(
                        modifier = Modifier
                            .padding(top = 8.dp),
                        text = "Try again ?",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W300,
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )

                    Box(
                        modifier = Modifier
                            .padding(top = 25.dp)
                            .width(200.dp)
                            .height(40.dp)
                            .background(color = Color.Red, shape = RoundedCornerShape(5.dp))
                            .clickable(enabled = true, onClick = {
                                 state.copy(
                                    errorState = false
                                )
                                viewModel.getCountries() })
                            .border(
                                width = 4.dp,
                                color = Color.Red,
                                shape = RoundedCornerShape(5.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(26.dp),
                            imageVector = Icons.Default.RotateLeft,
                            contentDescription = "Rotate Left",
                            tint = Color.White
                        )
                    }
                }
            }else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(750.dp)
                        .padding(horizontal = 20.dp)
                ) {

                    Spacer(modifier = Modifier.height(12.dp))


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "Select a country",
                            fontSize = 20.sp,
                            color = Color.Black
                        )

                        IconButton(
                            onClick = {
                                onDismiss()
                            }
                        ) {

                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Fechar",
                                tint = Color.Black
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))


                    BasicTextField(
                        value = state.search,

                        onValueChange = {
                            viewModel.updateSearch(it)
                        },

                        singleLine = true,

                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Black
                        ),

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFFF1F1F1))
                            .padding(horizontal = 16.dp),

                        decorationBox = { innerTextField ->

                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.CenterStart
                            ) {

                                if (state.search.isEmpty()) {

                                    Text(
                                        text = "Search for a country...",
                                        color = Color.Gray,
                                        fontSize = 16.sp
                                    )
                                }

                                innerTextField()
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(20.dp))


                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {

                        items(state.filteredCountries) { country ->

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onCountrySelected(country)
                                    }
                                    .padding(vertical = 14.dp),

                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    AsyncImage(
                                        model = country.flagPng,
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,

                                        modifier = Modifier
                                            .size(38.dp)
                                            .clip(CircleShape)
                                    )

                                    Spacer(modifier = Modifier.width(14.dp))

                                    Column {

                                        Text(
                                            text = country.name,
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color.Black
                                        )
                                    }
                                }
                            }

                            HorizontalDivider(
                                color = Color(0xFFEAEAEA)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}