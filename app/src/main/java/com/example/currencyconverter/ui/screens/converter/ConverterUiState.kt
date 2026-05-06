package com.example.currencyconverter.ui.screens.converter

import com.example.currencyconverter.network.model.ListCountrysResponse

data class ConverterUiState(
    val isLoading: Boolean = false,
    val alreadyMakeRequest: Boolean = false,
    val data: List<ListCountrysResponse> = emptyList(),
    val error: String? = null
)