package com.example.currencyconverter.data.repository

import com.example.currencyconverter.network.client.ListCountrysClient
import com.example.currencyconverter.network.model.ListCountrysResponse

class ListCountryRepository {

    suspend fun getCountries(): List<ListCountrysResponse>? {
        val response = ListCountrysClient.api.getCountries()

        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}