package com.android.mobile.simplejetpackcompose.nav

import androidx.navigation.NavHostController

object Destinations {
    const val Main = "main"
    const val Detail = "detail"
}

class Action(private val navController: NavHostController) {
    val navigateBack: () -> Unit = { navController.popBackStack() }

    fun <T>navigateBackWithState(keyName: String, value: T) : () -> Unit {
        return {
            navController.previousBackStackEntry
                ?.savedStateHandle
                ?.set(key = keyName, value = value)

            navController.popBackStack()
        }
    }
}

// pop back stack with parameter
// https://stackoverflow.com/questions/70609796/pass-data-to-previous-composable-in-android-compose
