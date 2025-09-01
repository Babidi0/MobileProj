package com.example.progetto

import TopBar
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.progetto.data.database.ProjDAO
import com.example.progetto.data.repositories.ProjectRepository
import com.example.progetto.ui.screen.BoatScreen
import com.example.progetto.ui.screen.BookingForm
import com.example.progetto.ui.screen.BookingScreen
import com.example.progetto.ui.screen.BookingState
import com.example.progetto.ui.screen.BookingViewModel
import com.example.progetto.ui.screen.HomeScreen
import com.example.progetto.ui.screen.LoadingScreen
import com.example.progetto.ui.screen.LoginForm
import com.example.progetto.ui.screen.ProfileScreen
import com.example.progetto.ui.screen.RegistrazioneScreen
import com.example.progetto.ui.screen.ThemeScreen
import com.example.progetto.ui.screen.ThemeState
import com.example.progetto.ui.screen.ThemeViewModel
import com.example.progetto.ui.screen.UserViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.LaunchedEffect
import com.example.progetto.ui.Composable.BottomBar
import com.example.progetto.utilities.BookingFormWrapper
import com.example.progetto.utilities.BookingScreenWrapper


sealed class NavigationRoute (
    val route: String
) {
    data object Home : NavigationRoute("Home")
    data object Profile: NavigationRoute("Profile")
    data object Boat: NavigationRoute("Boat")
    data object Booking: NavigationRoute("Booking")
    data object Register: NavigationRoute("Register")
    data object BookingForm: NavigationRoute("BookingForm")
    data object Login: NavigationRoute("Login")

    data object Theme:NavigationRoute("Theme")

}


@Composable
fun NavGraph(
    navController: NavHostController,
    themeState: ThemeState,
    themeViewModel: ThemeViewModel,
    bookingViewModel: BookingViewModel,
    userViewModel: UserViewModel,
    repository: ProjectRepository,
    db: ProjDAO
) {
    val sessionUser by repository.sessionUser.collectAsState(initial = null)

    NavHost(
        navController = navController,
        startDestination = NavigationRoute.Login.route
    ) {
        // --- Login ---
        composable(NavigationRoute.Login.route) {
            LoginForm(userViewModel, navController)
        }

        // --- Registrazione ---
        composable(NavigationRoute.Register.route) {
            RegistrazioneScreen(db, navController, userViewModel)
        }

        // --- Home ---
        composable(NavigationRoute.Home.route) {
            TopBar(navController, userViewModel) {
                HomeScreen(navController, userViewModel)
                BottomBar(navController)
            }
        }

        // --- Profile ---
        composable(NavigationRoute.Profile.route) {
            TopBar(navController, userViewModel) {
                ProfileScreen(navController, userViewModel, db, repository)
                BottomBar(navController)
            }
        }

        // --- Booking ---
        composable(NavigationRoute.Booking.route) {
            TopBar(navController, userViewModel) {
                if (sessionUser != null) {
                    BookingState(bookingViewModel, repository, navController, userViewModel)
                } else {
                    Text("Loading...")
                }
                BottomBar(navController)
            }
        }

        composable(NavigationRoute.BookingForm.route) {
            BookingForm(
                bookingViewModel = bookingViewModel,
                userId = sessionUser!!.first,
                navController = navController,
                viewModel = userViewModel
            )
        }

        composable(NavigationRoute.Theme.route) {
            ThemeScreen(themeState, themeViewModel::changeTheme, navController, userViewModel)
        }
        composable(NavigationRoute.Boat.route) {
            TopBar(navController, userViewModel) {
                BoatScreen()
                BottomBar(navController)
            }
        }
    }
}





