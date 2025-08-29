package com.example.progetto.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.progetto.data.database.Booking
import com.example.progetto.ui.Composable.DatePickerModal
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun BookingScreen(bookingViewModel: BookingViewModel,
                  userId:Int, onBookingSuccess: () -> Unit) {


    var boatId by remember { mutableStateOf("") }
    var numberOfPerson by remember { mutableStateOf("") }
    var bookingDate by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var datePicker by remember { mutableStateOf(false) }

    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    /*ci sarà una session nel momento in cui accedo al mio profilo nel frattempo impostiamo così*/
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
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = surname,
            onValueChange = { surname = it },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = boatId,
            onValueChange = { boatId = it },
            label = { Text("ID Boat") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = bookingDate,
            onValueChange = {  },
            label = { Text("Data") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { datePicker = true}) {
                    Icon(Icons.Default.DateRange, contentDescription = "Seleziona Data")
                }
            }
        )

        OutlinedTextField(
            value = numberOfPerson,
            onValueChange = { numberOfPerson = it },
            label = { Text("ID Boat") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
        )



        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            //bookingViewModel.addBooking(Booking(0,bookingDate,userId,boatId,numberOfPerson))
            //modifier = Modifier.fillMaxWidth()
        }) {Text("Conferma prenotazione")

        }

        //DatePicker
        if (datePicker) {
            DatePickerModal(onDateSelected = { millis ->
                millis?.let {
                    val date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        .format(Date(it))
                    bookingDate = date
                }
            },
                onDismiss = {datePicker = false})
        }





        /*Button(
            onClick = {
                val num = persone.toIntOrNull() ?: 1
                Toast.makeText(
                    context,
                    "Prenotazione per $nome il $data ($num persone)",
                    Toast.LENGTH_SHORT
                ).show()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Prenota")
        }

         */
    }
}
