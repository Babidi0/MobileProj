package com.example.progetto.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.progetto.data.database.ProjDAO
import com.example.progetto.data.database.User
import com.example.progetto.data.models.Theme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object PreferenceKeys {
    val User_ID = intPreferencesKey("user_id")
    val Username = stringPreferencesKey("username")
}
class ProjectRepository(private val projDAO: ProjDAO, private val dataStore: DataStore<Preferences>) {

    val profile: Flow<List<User>> = projDAO.getAll()

    suspend fun upsert(user: User) = projDAO.upsert(user)

    suspend fun delete(user: User) = projDAO.delete(user)

    companion object {
        private val THEME_KEY = stringPreferencesKey("theme")
    }

    val theme = dataStore.data
        .map { preferences ->
            try {
                Theme.valueOf(preferences[THEME_KEY] ?: "System")
            } catch (_: Exception) {
                Theme.System
            }
        }

    suspend fun setTheme(theme: Theme) =
        dataStore.edit { it[THEME_KEY] = theme.toString() }

    suspend fun saveUserSession(userId:Int, username: String) {
        dataStore.edit { prefs ->
            prefs[PreferenceKeys.User_ID] = userId
            prefs[PreferenceKeys.Username] = username
        }
    }

    val sessionUser = dataStore.data.map { preferences ->
        val id = preferences[PreferenceKeys.User_ID]
        val name = preferences[PreferenceKeys.Username]
        if (id != null && name != null) {
            Pair(id,name)
        } else {
            null
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}

