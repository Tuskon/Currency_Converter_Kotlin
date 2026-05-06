package com.example.currencyconverter.network.api

import com.example.currencyconverter.network.model.ListCountrysResponse
import retrofit2.Response
import retrofit2.http.GET

interface ListCountrysService {

    @GET("b?fields=name,currencies")
    suspend fun getCountries(): Response<List<ListCountrysResponse>>
}