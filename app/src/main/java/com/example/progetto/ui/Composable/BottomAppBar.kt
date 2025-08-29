package com.example.progetto.ui.Composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.progetto.NavigationRoute

@Composable
fun BottomBar(navController: NavHostController) {
    BottomAppBar(
        actions = {
            IconButton(onClick = {  navController.navigate(NavigationRoute.Home.route) }) {
                Icon(Icons.Default.Call, contentDescription = "Home")
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {  navController.navigate(NavigationRoute.Booking.route) }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Nuova prenotazione")
            }
        }
    )
}