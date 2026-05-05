package com.example.currencyconverter.network.model

data class ListCurrencysResponse(
    val base: String,
    val target: String,
    val rate : Float,
    val timestamp: String
)