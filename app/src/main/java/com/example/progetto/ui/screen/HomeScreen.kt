package com.example.progetto.ui.screen

import TopBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.progetto.ui.Composable.BottomBar

@Composable
fun HomeScreen(navController: NavHostController, viewModel: UserViewModel) {
    Scaffold(
        topBar = {TopBar(navController = navController, viewModel = viewModel)},
        bottomBar = { BottomBar(navController = navController) }


    ) {
        innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text("Evento")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color.Gray)
            ) { }
        }
    }

    }




/*ricordati di creare un form per la compilazione delle prenotazioni che appare in sovraimpressione*/