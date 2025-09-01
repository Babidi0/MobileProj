package com.example.progetto.ui.screen

import TopBar
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.progetto.data.database.Booking
import com.example.progetto.data.repositories.ProjectRepository
import com.example.progetto.ui.Composable.BottomBar

@Composable
fun BookingState(bookingViewModel: BookingViewModel, repository: ProjectRepository,navController: NavHostController, viewModel: UserViewModel) {

    val sessionUser by repository.sessionUser.collectAsState(initial = null)

    if (sessionUser != null) {
        val userId = sessionUser!!.first
        BookingScreen(userId = userId, bookingViewModel = bookingViewModel, navController,viewModel)
    } else {
        Text("Utente non loggato")
    }
}

@Composable
fun BookingScreen(userId:Int?, bookingViewModel: BookingViewModel, navController: NavHostController, viewModel: UserViewModel) {

    var currentBooking by remember { mutableStateOf<Booking?>(null) }

    LaunchedEffect(userId) {
        userId?.let {
            currentBooking = bookingViewModel.getCurrentBooking(it)
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        currentBooking?.let { booking ->
            Text("Prenotazione")
            Text("Barca ID: ${booking.idB}")
            Text("Data: ${booking.date}")
            Text("Numero persone: ${booking.number}")
        } ?: Text("Nessuna prenotazione")
    }
}