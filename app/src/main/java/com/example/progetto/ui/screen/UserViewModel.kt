package com.example.progetto.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.progetto.data.database.ProjDAO
import com.example.progetto.data.database.User
import com.example.progetto.data.repositories.ProjectRepository
import com.example.progetto.utilities.authenticateUser
import com.example.progetto.utilities.hashPassword
import com.example.progetto.utilities.verifyPassword
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

    class UserViewModel(private val dao: ProjDAO, private val repository: ProjectRepository) : ViewModel() {

        val sessionUser = repository.sessionUser.asLiveData()

        fun registerUser(
            username: String,
            password: String,
            img: String,
            email: String,
            name: String,
            surname: String,
            onResult: (Boolean) -> Unit
        ) {
            viewModelScope.launch {
                try {
                    // 1. Hash della password
                    val hashed = hashPassword(password)

                    // 2. Crea l’utente
                    val newUser = User(
                        id = 0,                  // Room genererà l'id automaticamente
                        username = username,
                        passwordHash = hashed,
                        userImg = img,
                        name = name,
                        surname = surname,
                        email = email
                    )

                    // 3. Inserisci l’utente nel DB
                    repository.addUser(newUser)

                    // 4. Recupera l’utente appena creato per ottenere l’ID
                    val userFromDb = repository.getUserByUsername(username)

                    if (userFromDb != null) {
                        // 5. Salva subito la sessione con userId e username
                        repository.saveUserSession(userFromDb.id, userFromDb.username)

                        // 6. Notifica che la registrazione è avvenuta con successo
                        onResult(true)
                    } else {
                        onResult(false)
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                    onResult(false)
                }
            }
        }

          fun login(username: String, password: String, onLogin: (Boolean) -> Unit) {
              viewModelScope.launch {
                  val user = withContext(Dispatchers.IO) {
                      repository.getUserByUsername(username)
                  }

                  val success = user?.let {
                      withContext(Dispatchers.IO) {
                          verifyPassword(password, it.passwordHash)
                      }
                  } ?: false

                  if (success) {
                      withContext(Dispatchers.IO) {
                          repository.saveUserSession(user!!.id, user.username)
                      }
                      onLogin(true)
                  } else {
                      onLogin(false)
                  }
              }
        }

        fun logout() = viewModelScope.launch {
            repository.logout()
        }
    }
