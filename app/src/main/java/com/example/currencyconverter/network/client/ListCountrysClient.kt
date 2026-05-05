package com.example.currencyconverter.network.client

import com.example.currencyconverter.network.api.ListCountrysService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ListCountrysClient {

    private const val BASE_URL = "https://restcountries.com/v3.1/"

    val api: ListCountrysService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ListCountrysService::class.java)
    }
}