package com.example.currencyconverter.data.repository

import com.example.currencyconverter.network.client.ListCurrencysClient
import com.example.currencyconverter.network.model.ListCurrencysResponse

class ListCurrencyRepository {

    suspend fun getCountries(): ListCurrencysResponse? {
        val response = ListCurrencysClient.api.getCurrency()

        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}