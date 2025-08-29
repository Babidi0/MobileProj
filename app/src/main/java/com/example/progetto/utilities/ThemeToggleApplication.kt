package com.example.progetto.utilities

import android.app.Application
import com.example.progetto.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ThemeToggleApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@ThemeToggleApplication)
            modules(appModule)
        }
    }
}