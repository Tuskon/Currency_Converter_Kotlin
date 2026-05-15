package com.example.currencyconverter.ui.components.countryBottomSheet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.network.client.ListCountrysClient
import kotlinx.coroutines.launch

class CountryBottomSheetViewModel : ViewModel() {

    var state by mutableStateOf(CountryBottomSheetUiState())

        private set

    init {
        getCountries()
    }

    fun getCountries() {
        viewModelScope.launch {

            state = state.copy(isLoading = true)

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

                        val flag = item.flags.png

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


                } else {

                    state = state.copy(
                        isLoading = false,
                        errorState = true
                    )
                }

            } catch (e: Exception) {


                state = state.copy(
                    isLoading = false,
                    errorState = true
                )
            }
        }
    }

    fun updateSearch(value: String) {

        val filtered = state.countries.filter {
            it.name.contains(
                value,
                ignoreCase = true
            )
        }

        state = state.copy(
            search = value,
            filteredCountries = filtered
        )
    }
}