package com.example.progetto

import android.Manifest
import android.content.res.Resources.Theme
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.benchmark.perfetto.Row
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.progetto.ui.screen.ThemeScreen
import com.example.progetto.ui.screen.ThemeViewModel
import com.example.progetto.ui.theme.ProgettoTheme
import com.example.progetto.utilities.LocationService
import com.example.progetto.utilities.PermissionStatus
import com.example.progetto.utilities.rememberCameraLauncher
import com.example.progetto.utilities.rememberPermission
import org.koin.androidx.compose.koinViewModel


class MainActivity : ComponentActivity() {

    private lateinit var locationService: LocationService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {



            val cameraLauncher = rememberCameraLauncher()
            var showLocationDisabledAlert by remember {
                mutableStateOf(false)
            }
            var showPermissionDeniedAlert by remember {
                mutableStateOf(false)
            }
            var showPermissionPermissionPermanentlyDeniedSnackbar by remember {
                mutableStateOf(false)
            }

            //variabili per i messaggi di warning che verranno mostrati in caso di permessi negati
            val locationPermission = rememberPermission(Manifest.permission.ACCESS_COARSE_LOCATION){
                status ->
                when (status) {
                    PermissionStatus.Granted -> locationService.requestCurrentLocation()
                    PermissionStatus.Denied ->  showPermissionDeniedAlert = true
                    PermissionStatus.PermanentlyDenied -> showPermissionPermissionPermanentlyDeniedSnackbar = true
                    PermissionStatus.Unknown -> {}
                }
            }
            val ctx = LocalContext.current
            //PermissionStatus == permanentlyDenied show a snackbar for edit the permission from the settings


            val cameraPermission = rememberPermission(Manifest.permission.CAMERA) { status ->
                if (status.isGranted) {
                    cameraLauncher.captureImage()
                } else {
                    Toast.makeText(ctx, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
            val themeViewModel = koinViewModel<ThemeViewModel>()
            val themeState by themeViewModel.state.collectAsStateWithLifecycle()

            //funzione che gestisce l'utilizzo della fotocamera
            fun takePicture() =
                if (cameraPermission.status.isGranted) {
                    cameraLauncher.captureImage()
                } else {
                    cameraPermission.launchPermissionRequest()
                }
            //funzione che gestisce il permesso per l'accesso alla posizione (da assegnare a un button)
            fun requestLocation() =
                if(locationPermission.status.isGranted){
                    locationService.requestCurrentLocation()
                } else {
                    locationPermission.launchPermissionRequest()
                }


            /*
            ProgettoTheme(darkTheme = when (themeState.theme) {
                Theme.Dark -> true
                Theme.Light -> false
                Theme.System -> isSystemInDarkTheme()
            }) {


                }

             */
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                  ThemeScreen(themeState, themeViewModel::changeTheme)
                    Greeting("Android")
                }
            }
        }
    }



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProgettoTheme {
        Greeting("Android")
    }
}