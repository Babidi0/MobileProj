

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.progetto.NavigationRoute
import com.example.progetto.ui.screen.UserViewModel
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class)
// [START android_compose_components_detaileddrawerexample]
@Composable
fun TopBar(
     navController: NavHostController,
     viewModel: UserViewModel
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(12.dp))
                    Text("Boat on Loan", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleLarge)

                    NavigationDrawerItem(
                        label = { Text("Profile") },
                        icon = {Icon(Icons.Default.Contacts, contentDescription = "Home")},
                        selected = false,
                        onClick = { navController.navigate(NavigationRoute.Profile.route) }
                    )
                    NavigationDrawerItem(
                        label = { Text("Booking") },
                        icon = {Icon(Icons.Filled.Image, contentDescription = "Galleria")},
                        selected = false,
                        onClick = { navController.navigate(NavigationRoute.Booking.route) }
                    )
                    NavigationDrawerItem(
                        label = { Text("Map") },
                        icon = {Icon(Icons.Default.LocationOn, contentDescription = "Mappa")},
                        selected = false,
                        onClick = { /* Handle click */ }
                    )

                    NavigationDrawerItem(
                        label = { Text("Logout") },
                        icon = {Icon(Icons.Default.Close, contentDescription = "Logout")},
                        selected = false,
                        onClick = {
                            scope.launch {
                                viewModel.logout()
                                navController.navigate(NavigationRoute.Login.route) {
                                    popUpTo(0) {inclusive = true}
                                }
                            }
                        }
                    )

                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("BoatOnLoan") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    scrollBehavior = scrollBehavior
                )
            }
        ) { innerPadding ->
            Modifier.padding(innerPadding)
        }
    }
}
