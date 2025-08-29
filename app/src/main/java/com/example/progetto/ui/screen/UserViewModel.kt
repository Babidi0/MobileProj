package com.example.progetto.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.progetto.data.database.ProjDAO
import com.example.progetto.utilities.authenticateUser
import com.example.progetto.utilities.registerUser
import kotlinx.coroutines.launch

class UserViewModel {
    class UserViewModel(private val dao: ProjDAO) : ViewModel() {

        fun registerUser(username: String, password: String, img: String, email: String, name: String, surname: String) {
            viewModelScope.launch {
                registerUser(username, password, img, dao)
            }
        }

        suspend fun login(username: String, password: String): Boolean {
            return authenticateUser(username, password, dao)
        }
    }
}