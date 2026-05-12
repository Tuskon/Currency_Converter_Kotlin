package com.example.currencyconverter.ui.components.countryBottomSheet

import com.example.currencyconverter.network.model.CurrencyDto

data class Country(
    val name: String,
    val currencies: Map<String, CurrencyDto> = emptyMap(),
    val flagPng: String
)

data class CurrencyDto(
    val name: String,
    val symbol: String
)