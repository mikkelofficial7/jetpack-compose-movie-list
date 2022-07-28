package com.android.mobile.simplejetpackcompose.view

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import com.android.mobile.simplejetpackcompose.ui.theme.Purple500

@Composable
fun topNavigationBar(titleText: String, showBack: Boolean = false, action: () -> Unit) {

    val iconBack: (@Composable () -> Unit)? = when(showBack) {
        true -> {
            {
                IconButton(onClick = action) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "arrow back")
                }
            }
        }
        else -> null
    }

    TopAppBar(
        title = { Text(text = titleText) },
        backgroundColor = Purple500,
        navigationIcon = iconBack
    )
}