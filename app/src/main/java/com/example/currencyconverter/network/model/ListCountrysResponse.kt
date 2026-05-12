package com.example.currencyconverter.network.model

data class ListCountrysResponse(
    val name: NameDto,
    val currencies: Map<String, CurrencyDto> = emptyMap(),
    val flags: FlagDto
)

data class NameDto(
    val common: String,
    val official: String,
    val nativeName: Map<String, NativeNameDto> = emptyMap()
)

data class NativeNameDto(
    val official: String,
    val common: String
)

data class CurrencyDto(
    val name: String,
    val symbol: String
)

data class FlagDto(
    val png: String,
    val svg: String,
    val alt: String
)