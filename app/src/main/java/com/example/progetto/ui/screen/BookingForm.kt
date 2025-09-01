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
import com.example.progetto.ui.Composable.BottomBar
import com.example.progetto.ui.Composable.DatePickerModal
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingForm(bookingViewModel: BookingViewModel,
                userId:Int, navController: NavHostController, viewModel: UserViewModel.UserViewModel) {


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
    Scaffold(
        topBar = {TopBar(navController = navController, viewModel = viewModel)},
        bottomBar = { BottomBar(navController = navController)}
    ) {
        innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
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
                label = { Text("Number of Person") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
            )



            Spacer(Modifier.height(16.dp))

            Button(onClick = {

                val boatIdInt = boatId.toIntOrNull()
                val numPersons = numberOfPerson.toIntOrNull()

                //bookingViewModel.addBooking(Booking(0,bookingDate,userId,boatIdInt,numPersons))
            },
                modifier = Modifier.fillMaxWidth())
            {Text("Confirm")

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
