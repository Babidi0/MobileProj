package com.example.progetto

import androidx.compose.foundation.isSystemInDarkTheme
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
import com.example.progetto.ui.screen.LoginForm
import com.example.progetto.ui.screen.ProfileScreen
import com.example.progetto.ui.screen.RegistrazioneScreen
import com.example.progetto.ui.screen.ThemeScreen
import com.example.progetto.ui.screen.ThemeState
import com.example.progetto.ui.screen.ThemeViewModel
import com.example.progetto.ui.screen.UserViewModel
import org.koin.androidx.compose.koinViewModel

sealed class NavigationRoute (
    val route: String
) {
    data object Home : NavigationRoute("Home")
    data object Profile: NavigationRoute("Profile")
    data object Boat: NavigationRoute("Boat")
    data object Booking: NavigationRoute("Booking")
    data object Register: NavigationRoute("Register")
    data object BookingForm: NavigationRoute("Gallery")
    data object Login: NavigationRoute("Login")

    data object Theme:NavigationRoute("Theme")

}




@Composable
fun NavGraph (navController: NavHostController,
              modifier: Modifier = Modifier,
              themeState: ThemeState,
              themeViewModel: ThemeViewModel,
              bookingViewModel: BookingViewModel,
              userViewModel: UserViewModel.UserViewModel,
              repository: ProjectRepository,
              db: ProjDAO) {

    val sessionUser by repository.sessionUser.collectAsState(initial = null )
    val userId = sessionUser?.first
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.Login.route,

    ) {


        with(NavigationRoute.Home) {
            composable(route) { HomeScreen(navController,userViewModel) }
        }

        with(NavigationRoute.Register) {
            composable(route) { RegistrazioneScreen(db, navController) }
        }
        with(NavigationRoute.Login) {
            composable(route) { LoginForm(userViewModel ,  navController) }
        }
        with(NavigationRoute.BookingForm) {
            //BookingForm(bookingViewModel = bookingViewModel, userId = userId)
            composable(route) {
                userId?.let {
                    BookingForm(bookingViewModel = bookingViewModel, id, navController, userViewModel)

                }
            }
        }

        with(NavigationRoute.Boat) {
            composable(route) { BoatScreen(navController = navController, viewModel = userViewModel)}
        }
        
        with(NavigationRoute.Profile) {
            composable(route) { ProfileScreen(navController,userViewModel,db,repository)}
        }
        with(NavigationRoute.Booking) {
            composable(route) { BookingState(bookingViewModel = bookingViewModel, repository, navController, userViewModel)}
        }
        with(NavigationRoute.Theme) {
            composable(route) { ThemeScreen(themeState, themeViewModel::changeTheme, navController, userViewModel) }
        }


    }
}


