package com.example.progetto.ui.Composable

import android.text.Layout
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Anchor
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.progetto.NavigationRoute

// BottomBar.kt
@Composable
fun BottomBar(navController: NavHostController) {
    BottomAppBar(
        actions = {
            IconButton(
                onClick = {
                    navController.navigate(NavigationRoute.Home.route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            ) {
                Icon(Icons.Default.Anchor, contentDescription = "Home")
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(NavigationRoute.BookingForm.route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                containerColor = Color.Magenta
            ) {
                Icon(Icons.Default.Add, contentDescription = "Nuova prenotazione")
            }
        }
    )
}
