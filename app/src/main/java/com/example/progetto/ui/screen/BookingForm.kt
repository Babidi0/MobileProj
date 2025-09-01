package com.example.progetto.ui.screen

import TopBar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.progetto.data.database.Booking
import com.example.progetto.ui.Composable.BottomBar
import com.example.progetto.ui.Composable.DatePickerModal
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun BookingForm(
    bookingViewModel: BookingViewModel,
    userId: Int,
    navController: NavHostController,
    viewModel: UserViewModel
) {

    var boatId by remember { mutableStateOf("") }
    var numberOfPerson by remember { mutableStateOf("") }
    var bookingDate by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var datePicker by remember { mutableStateOf(false) }

    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    // Recupera i dati dell'utente
    LaunchedEffect(userId) {
        val register = bookingViewModel.getRegister(userId)
        register?.let {
            name = it.name
            surname = it.surname
            email = it.email
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nome") }
        )

        OutlinedTextField(
            value = surname,
            onValueChange = { surname = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Cognome") }
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Email") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = boatId,
            onValueChange = { boatId = it },
            label = { Text("ID Barca") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = bookingDate,
            onValueChange = { },
            label = { Text("Data") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { datePicker = true }) {
                    Icon(Icons.Default.DateRange, contentDescription = "Seleziona Data")
                }
            }
        )

        OutlinedTextField(
            value = numberOfPerson,
            onValueChange = { numberOfPerson = it },
            label = { Text("Numero Persone") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {
                val boatIdInt = boatId.toIntOrNull()
                val numPersons = numberOfPerson.toIntOrNull()

                if (boatIdInt != null && numPersons != null && bookingDate.isNotEmpty()) {
                    bookingViewModel.addBooking(
                        Booking(
                            idBooking = 0,
                             bookingDate,
                            userId,
                            boatIdInt,
                            numPersons
                        )
                    )
                    errorMessage = ""
                } else {
                    errorMessage = "Inserisci valori validi per ID Barca, Data e Numero Persone"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Conferma Prenotazione")
        }

        // DatePicker Modal
        if (datePicker) {
            DatePickerModal(
                onDateSelected = { millis ->
                    millis?.let {
                        val date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                            .format(Date(it))
                        bookingDate = date
                    }
                    datePicker = false
                },
                onDismiss = { datePicker = false }
            )
        }
    }
}
