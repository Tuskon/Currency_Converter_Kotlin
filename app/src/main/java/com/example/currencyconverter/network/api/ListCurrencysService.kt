package com.example.currencyconverter.network.api

import com.example.currencyconverter.network.model.ListCurrencysResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ListCurrencysService {

    @GET("api/{firstCountry}/{secondCountry}.json")
    suspend fun getCurrency(
        @Path("firstCountry") firstCountry: String,
        @Path("secondCountry") secondCountry: String
    ): Response<ListCurrencysResponse>
}