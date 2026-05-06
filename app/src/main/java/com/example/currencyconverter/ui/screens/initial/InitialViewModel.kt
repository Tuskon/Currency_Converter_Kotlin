package com.example.currencyconverter.ui.screens.initial

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class InitialViewModel : ViewModel() {

    fun goToConverterScreen (navController: NavController){
        navController.navigate("converter")
    }

}