package com.example.progetto.ui.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.progetto.NavigationRoute
import com.example.progetto.data.database.ProjDAO
import com.example.progetto.utilities.rememberCameraLauncher
import com.example.progetto.utilities.rememberPermission
import kotlinx.coroutines.launch


@Composable
fun RegistrazioneScreen(dao: ProjDAO, navController: NavHostController, userViewModel: UserViewModel) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    // Permission handler
    val cameraPermission = rememberPermission(android.Manifest.permission.CAMERA)
    val galleryPermission = rememberPermission(android.Manifest.permission.READ_MEDIA_IMAGES)

    // Camera handler (usa il tuo)
    val cameraLauncher = rememberCameraLauncher { imageUri ->
        selectedImageUri = imageUri
    }

    // Galleria launcher
    val galleryLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            selectedImageUri = uri
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Sign Up", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("username") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = surname,
            onValueChange = { surname = it },
            label = { Text("Surname") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                if (galleryPermission.status.isGranted) {
                    galleryLauncher.launch("image/*")
                } else {
                    galleryPermission.launchPermissionRequest()
                }
            }) {
                Text("Gallery")
            }

            Button(onClick = {
                if (cameraPermission.status.isGranted) {
                    cameraLauncher.captureImage()
                } else {
                    cameraPermission.launchPermissionRequest()
                }
            }) {
                Text("Camera")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        selectedImageUri?.let { uri ->
            Image(
                painter = rememberAsyncImagePainter(uri),
                contentDescription = "Immagine profilo",
                modifier = Modifier
                    .size(120.dp).clip(CircleShape)
            )
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                scope.launch {
                    // Registrazione utente
                    userViewModel.registerUser(
                        username = username,
                        password = password,
                        img = selectedImageUri?.toString() ?: "",
                        email = email,
                        name = name,
                        surname = surname
                    ) { success ->
                        if (success) {
                            navController.navigate(NavigationRoute.Home.route) {
                                popUpTo(NavigationRoute.Register.route) { inclusive = true }
                            }
                        } else {
                            // Mostra messaggio di errore
                        }
                    }
                }

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrati")
        }
    }


}

