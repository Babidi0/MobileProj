package com.example.progetto.ui.screen

import TopBar
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.progetto.ui.Composable.BottomBar

@Composable
fun BoatScreen(navController: NavHostController, viewModel: UserViewModel) {
    Scaffold(
        topBar = {TopBar(navController = navController, viewModel = viewModel)},
        bottomBar = { BottomBar(navController = navController)}
        
    ) {
        innerPadding ->

        Column {

        }

    }
}
