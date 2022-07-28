package com.android.mobile.simplejetpackcompose.nav

import androidx.navigation.NavHostController

object Destinations {
    const val Main = "main"
    const val Detail = "detail"
}

class Action(navController: NavHostController) {
    val navigateBack: () -> Unit = { navController.popBackStack() }
}