package com.example.currencyconverter.ui.components.countryBottomSheet

data class CountryBottomSheetUiState(

    val search: String = "",

    val countries: List<Country> = emptyList(),

    val filteredCountries: List<Country> = emptyList(),

    val isLoading: Boolean = true
)