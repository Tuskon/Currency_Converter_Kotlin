package com.example.currencyconverter.network.client

import com.example.currencyconverter.network.api.ListCurrencysService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ListCurrencysClient {

    private const val BASE_URL = "https://fxapi.app/"

    val api: ListCurrencysService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ListCurrencysService::class.java)
    }
}