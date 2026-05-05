package com.example.currencyconverter.network.api

import com.example.currencyconverter.network.model.ListCurrencysResponse
import retrofit2.Response
import retrofit2.http.GET

interface ListCurrencysService {

    @GET("USD/DKK.json")
    suspend fun getCurrency(): Response<ListCurrencysResponse>
}