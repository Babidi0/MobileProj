package com.example.progetto.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.progetto.data.database.ProjDAO
import com.example.progetto.data.repositories.ProjectRepository
import com.example.progetto.utilities.authenticateUser
import com.example.progetto.utilities.hashPassword
import com.example.progetto.utilities.registerUser
import kotlinx.coroutines.launch

class UserViewModel {
    class UserViewModel(private val dao: ProjDAO, private val repository: ProjectRepository) : ViewModel() {

        val sessionUser = repository.sessionUser.asLiveData()

        suspend fun registerUser(username: String, password: String, img: String, email: String, name: String, surname: String) {
            viewModelScope.launch {
                registerUser(username, password, img, dao)
            }


        }

        suspend fun login(username: String, password: String, onLogin: (Boolean) -> Unit) {
            val user = dao.getUserByUsername(username)
            val success = authenticateUser(username,password,dao)

            if (user!=null){
                repository.saveUserSession(user.id,user.username)
                onLogin(true)
            } else {
                onLogin(false)
            }

        }

        fun logout() = viewModelScope.launch {
            repository.logout()
        }
    }
}