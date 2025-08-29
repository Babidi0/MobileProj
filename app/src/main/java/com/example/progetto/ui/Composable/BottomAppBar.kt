package com.example.progetto.ui.Composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.example.progetto.NavigationRoute

@Composable
fun BottomBar() {
    BottomAppBar(
        actions = {
            IconButton(onClick = { /* navController.navigate("home") */ }) {
                Icon(Icons.Default.Call, contentDescription = "Home")
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {  NavigationRoute.Booking }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Nuova prenotazione")
            }
        }
    )
}