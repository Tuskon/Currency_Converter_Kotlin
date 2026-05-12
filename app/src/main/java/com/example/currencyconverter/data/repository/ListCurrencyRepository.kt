package com.example.currencyconverter.data.repository

import com.example.currencyconverter.network.client.ListCurrencysClient
import com.example.currencyconverter.network.model.ListCurrencysResponse

class ListCurrencyRepository {

    suspend fun getCurrencies(
        firstCountry: String,
        secondCountry: String
    ): ListCurrencysResponse? {

        val response = ListCurrencysClient.api.getCurrency(
            firstCountry,
            secondCountry
        )

        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}