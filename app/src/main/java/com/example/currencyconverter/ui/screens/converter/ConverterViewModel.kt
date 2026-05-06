package com.example.currencyconverter.ui.screens.converter

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.currencyconverter.data.repository.ListCountryRepository
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.launch



class ConverterViewModel : ViewModel() {

    private val repository = ListCountryRepository()

    var state by mutableStateOf(ConverterUiState())
        private set

    fun loadCountries() {

        viewModelScope.launch {

            state = state.copy(alreadyMakeRequest= true,isLoading = true)

            try {
                val result = repository.getCountries()
                Log.d("MyRequest", "Resultado: ${result}")
                state = state.copy(
                    isLoading = false,
                    data = result ?: emptyList()
                )

            } catch (e: Exception) {
                Log.d("MyRequest", "Error: ${e.message}")
                state = state.copy(
                    isLoading = false,
                    error = e.message,

                )
            }
        }
    }

    fun goBack (navController: NavController){
        navController.popBackStack()
    }
}