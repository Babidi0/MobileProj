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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.progetto.data.database.ProjDAO
import com.example.progetto.data.database.ProjectDatabase
import com.example.progetto.data.repositories.ProjectRepository
import com.example.progetto.ui.screen.BookingViewModel
import com.example.progetto.ui.screen.HomeScreen
import com.example.progetto.ui.screen.LoginForm
import com.example.progetto.ui.screen.ProfileScreen
import com.example.progetto.ui.screen.ThemeScreen
import com.example.progetto.ui.screen.ThemeViewModel
import com.example.progetto.ui.screen.UserViewModel
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

        val db = ProjectDatabase.getDatabase(applicationContext)
        val dao = db!!.DAO()
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
            val bookingViewModel = koinViewModel<BookingViewModel>()
            val userViewModel = koinViewModel<UserViewModel>()
            val themeState by themeViewModel.state.collectAsStateWithLifecycle()
            val repository = ProjectRepository(dao,applicationContext.dataStore)
            

            ProgettoTheme(
                darkTheme = when (themeState.theme) {
                    com.example.progetto.data.models.Theme.Light -> false
                    com.example.progetto.data.models.Theme.Dark -> true
                    com.example.progetto.data.models.Theme.System -> isSystemInDarkTheme()
                }
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavGraph(
                        navController = navController,
                        themeState = themeState,
                        themeViewModel = themeViewModel,
                        userViewModel = userViewModel,
                        db = dao,
                        repository = repository,
                        bookingViewModel = bookingViewModel
                    )
                    /*
                    NavHost(
                        navController = navController,
                        startDestination = "login"
                    ) {
                        composable("login") { LoginForm(userViewModel, navController) }
                        composable("theme") { ThemeScreen(themeState, themeViewModel::changeTheme) }
                    }

                     */
                }

            }

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

            }
        }
    }
