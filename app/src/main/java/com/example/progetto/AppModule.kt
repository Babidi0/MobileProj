package com.example.progetto

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.example.progetto.data.database.ProjectDatabase
import com.example.progetto.data.repositories.ProjectRepository
import com.example.progetto.data.repositories.ThemeRepository
import com.example.progetto.ui.screen.ThemeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val Context.dataStore by preferencesDataStore("theme")

val appModule = module { 
    single { get<Context>().dataStore }
    
    single { ThemeRepository(get()) }

    single { Room.databaseBuilder(
        get(),
        ProjectDatabase::class.java,
        "Project"
    ).build()
    }

    single { ProjectRepository(get<ProjectDatabase>().DAO()) }
    
    viewModel { ThemeViewModel(get()) }
}