package com.example.progetto

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.progetto.data.database.ProjDAO
import com.example.progetto.ui.screen.BoatScreen
import com.example.progetto.ui.screen.HomeScreen
import com.example.progetto.ui.screen.LoginForm
import com.example.progetto.ui.screen.RegistrazioneScreen
import com.example.progetto.ui.screen.ThemeState
import com.example.progetto.ui.screen.ThemeViewModel

sealed class NavigationRoute (
    val route: String
) {
    data object Home : NavigationRoute("Home")
    data object Profile: NavigationRoute("Profile")
    data object Boat: NavigationRoute("Boat")
    data object Booking: NavigationRoute("Booking")
    data object Register: NavigationRoute("Register")
    data object Gallery: NavigationRoute("Gallery")
    data object Login: NavigationRoute("Login")

}


@Composable
fun NavGraph (navController: NavHostController,
              modifier: Modifier = Modifier,
              themeState: ThemeState,
              themeViewModel: ThemeViewModel,
              db: ProjDAO) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.Home.route,
        modifier
    ) {

        /*tutti i campi e viewModel da inserire*/
        val userVm =

        with(NavigationRoute.Home) {
            composable(route) { HomeScreen() }
        }
        with(NavigationRoute.Boat) {
            composable(route) { BoatScreen() }
        }
        with(NavigationRoute.Register) {
            composable(route) { RegistrazioneScreen(db, navController) }
        }
        with(NavigationRoute.Login) {
            composable(route) { LoginForm(viewModel = ,  navController) }
        }
        with(NavigationRoute.Gallery) {}
        with(NavigationRoute.Profile) {}
    }
}


