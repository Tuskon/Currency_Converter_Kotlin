package com.example.currencyconverter.ui.screens.converter

import com.example.currencyconverter.network.model.ListCountrysResponse

data class ConverterUiState(
    val isLoading: Boolean = false,
    val alreadyMakeRequest: Boolean = false,
    val data: List<ListCountrysResponse> = emptyList(),
    val error: String? = null,
    val valueFirstCountry: String? = null,
    val valueSecondCountry: String? = null,
    val firstCountryDto: FirstCountryDto? = null,
    val secondCountryDto: SecondCountryDto? = null,
    val showFirstBottomSheet: Boolean = false,
    val showSecondBottomSheet: Boolean = false,
    val isLoadingFirstCountry: Boolean = false,
    val isLoadingSecondCountry: Boolean = false,
    val failCurrencyRequest: Boolean = false,
    val rate: Double? = 1.0,
)

data class FirstCountryDto(
    val name: String,
    val currency: String,
    val png: String
)

data class SecondCountryDto(
    val name: String,
    val currency: String,
    val png: String
)