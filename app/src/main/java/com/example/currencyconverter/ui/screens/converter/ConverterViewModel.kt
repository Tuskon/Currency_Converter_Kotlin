package com.example.currencyconverter.ui.screens.converter

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.currencyconverter.network.client.ListCurrencysClient
import com.example.currencyconverter.ui.components.countryBottomSheet.Country
import kotlinx.coroutines.launch

class ConverterViewModel : ViewModel() {

    var state by mutableStateOf(ConverterUiState())
        private set

    @SuppressLint("DefaultLocale")
    private fun getCurrencys(
        first: String,
        second: String,
        isFirstCountry: Boolean,
        isSecondCountry: Boolean
    ) {
        viewModelScope.launch {
            if (isFirstCountry) {
                state = state.copy(
                    isLoadingFirstCountry = true,
                )
            }

            if (isSecondCountry) {
                state = state.copy(
                    isLoadingSecondCountry = true,
                )
            }

            try {

                val response = ListCurrencysClient.api.getCurrency(first, second)

                if (response.isSuccessful) {

                    val body = response.body()

                    val actualRate = body?.rate ?: 1.0

                    state = state.copy(
                        rate = actualRate,
                        isLoadingFirstCountry = false,
                        isLoadingSecondCountry = false
                    )


                } else {

                    state = state.copy(
                        isLoadingFirstCountry = false,
                        isLoadingSecondCountry = false,
                        failCurrencyRequest = false
                    )
                }

            } catch (e: Exception) {

                state = state.copy(
                    isLoadingFirstCountry = false,
                    isLoadingSecondCountry = false,
                    failCurrencyRequest = true
                )

            }
        }
    }

    private fun convertFirstToSecond(value: Double): Double {
        return value * (state.rate ?: 1.0)
    }

    private fun convertSecondToFirst(value: Double): Double {
        return value / (state.rate ?: 1.0)
    }

    private fun formatCurrencyInput(value: String): String {

        val replaced = value.replace(",", ".")

        val filtered = replaced.filter {
            it.isDigit() || it == '.'
        }

        val parts = filtered.split(".")

        return when {

            parts.isEmpty() -> ""

            parts.size == 1 -> {
                parts[0]
            }

            else -> {

                val integerPart = parts[0]
                val decimalPart = parts[1].take(2)

                "$integerPart.$decimalPart"
            }
        }
    }

    @SuppressLint("DefaultLocale")
    fun updateFirstValue(value: String) {

        val formatted = formatCurrencyInput(value)

        if (formatted.isEmpty()) {
            state = state.copy(
                valueFirstCountry = "",
                valueSecondCountry = ""
            )
            return
        }

        val number = formatted.toDoubleOrNull() ?: 0.0

        state = state.copy(
            valueFirstCountry = formatted,
            valueSecondCountry = String.format(
                java.util.Locale.US,
                "%.2f",
                convertFirstToSecond(number)
            )
        )
    }

    @SuppressLint("DefaultLocale")
    fun updateSecondValue(value: String) {

        val formatted = formatCurrencyInput(value)

        if (formatted.isEmpty()) {
            state = state.copy(
                valueSecondCountry = "",
                valueFirstCountry = ""
            )
            return
        }

        val number = formatted.toDoubleOrNull() ?: 0.0

        state = state.copy(
            valueSecondCountry = formatted,
            valueFirstCountry = String.format(
                java.util.Locale.US,
                "%.2f",
                convertSecondToFirst(number)
            )
        )
    }

    fun updateFirstCountryDto(value: Country) {

        val currencieFormatted = value.currencies.keys.firstOrNull() ?: ""

        state = state.copy(
            firstCountryDto = FirstCountryDto(
                name = value.name,
                currency = currencieFormatted,
                png = value.flagPng
            )
        )

        if (state.firstCountryDto != null && state.secondCountryDto != null) {

            getCurrencys(
                currencieFormatted,
                state.secondCountryDto!!.currency,
                true,
                false
            )
        }
    }

    fun updateSecondCountryDto(value: Country) {

        val currencieFormatted = value.currencies.keys.firstOrNull() ?: ""

        state = state.copy(
            secondCountryDto = SecondCountryDto(
                name = value.name,
                currency = currencieFormatted,
                png = value.flagPng
            )
        )

        if (state.firstCountryDto != null && state.secondCountryDto != null) {

            getCurrencys(
                state.firstCountryDto!!.currency,
                currencieFormatted,
                false,
                true
            )
        }
    }

    fun optionFirstBottomSheet(show: Boolean) {

        state = state.copy(
            showFirstBottomSheet = show
        )
    }

    fun optionSecondBottomSheet(show: Boolean) {

        state = state.copy(
            showSecondBottomSheet = show
        )
    }


    fun madeCurrencyRequestAgain() {
        state = state.copy(
            failCurrencyRequest = false
        )

        getCurrencys(
            state.firstCountryDto!!.currency,
            state.secondCountryDto!!.currency,
            true,
            true
        )
    }

    fun goBackToInitialScreen(navController: NavController,choice: Boolean) {
        if(choice){
            state = state.copy(
                failCurrencyRequest = false
            )
        }else{
            state = state.copy(
                showFirstBottomSheet = false,
                showSecondBottomSheet = false
            )
        }
        navController.popBackStack()
    }

}