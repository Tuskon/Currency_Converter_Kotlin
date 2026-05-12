package com.example.currencyconverter.ui.components.countryBottomSheet

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.network.client.ListCountrysClient
import kotlinx.coroutines.launch
import androidx.compose.ui.graphics.Brush

class CountryBottomSheetViewModel : ViewModel() {

    var state by mutableStateOf(
        CountryBottomSheetUiState()
    )
        private set

    init {
        getCountries()
    }

    private fun getCountries() {

        viewModelScope.launch {

            state = state.copy(
                isLoading = true
            )

            try {

                val response = ListCountrysClient.api.getCountries()

                if (response.isSuccessful) {

                    val countries = response.body()?.map { item ->

                        val currency =
                            item.currencies
                                ?.entries
                                ?.firstOrNull()
                                ?.value
                                ?.name ?: ""

                        val flag =
                            item.flags.png

                        Country(
                            name = item.name?.common ?: "",
                            currencies = item.currencies,
                            flagPng = flag
                        )

                    } ?: emptyList()

                    state = state.copy(
                        countries = countries,
                        filteredCountries = countries,
                        isLoading = false
                    )

                    Log.d("Req", "$response")

                } else {

                    Log.d("Req", "$response")

                    state = state.copy(
                        isLoading = false
                    )
                }

            } catch (e: Exception) {

                Log.d("Req", "$e")

                state = state.copy(
                    isLoading = false
                )
            }
        }
    }

    fun updateSearch(value: String) {

        val filtered = state.countries.filter {

            it.name.contains(value, ignoreCase = true)
        }

        state = state.copy(
            search = value,
            filteredCountries = filtered
        )
    }

}

