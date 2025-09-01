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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Anchor
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.progetto.NavigationRoute
import com.example.progetto.ui.Composable.BottomBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: UserViewModel) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = "Boat on Loan",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(16.dp)
                    )

                    NavigationDrawerItem(
                        label = { Text("Profile") },
                        icon = { Icon(Icons.Default.Contacts, contentDescription = "Profile") },
                        selected = false,
                        onClick = {
                            navController.navigate(NavigationRoute.Profile.route)
                        }
                    )

                    NavigationDrawerItem(
                        label = { Text("Booking") },
                        icon = { Icon(Icons.Default.Image, contentDescription = "Booking") },
                        selected = false,
                        onClick = {
                            navController.navigate(NavigationRoute.Booking.route)
                        }
                    )

                    NavigationDrawerItem(
                        label = { Text("Logout") },
                        icon = { Icon(Icons.Default.Close, contentDescription = "Logout") },
                        selected = false,
                        onClick = {
                            scope.launch {
                                viewModel.logout()
                                navController.navigate(NavigationRoute.Login.route) {
                                    popUpTo(0) { inclusive = true }
                                }
                            }
                        }
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Boat on Loan") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) drawerState.open() else drawerState.close()
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    scrollBehavior = scrollBehavior
                )
            },
            bottomBar = {
                BottomAppBar(
                    actions = {
                        IconButton(
                            onClick = {
                                navController.navigate(NavigationRoute.Home.route) {
                                    launchSingleTop = true
                                    restoreState = true
                                    popUpTo(NavigationRoute.Home.route) { inclusive = false }
                                }
                            }
                        ) {
                            Icon(Icons.Default.Anchor, contentDescription = "Home")
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                navController.navigate(NavigationRoute.BookingForm.route) {
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            containerColor = Color.Magenta
                        ) {
                            Icon(Icons.Default.Add, contentDescription = "Nuova prenotazione")
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                // Qui puoi mettere contenuti generici della home se vuoi
                Text("Benvenuto!", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(16.dp))
            }
        }
    }
}






/*ricordati di creare un form per la compilazione delle prenotazioni che appare in sovraimpressione*/