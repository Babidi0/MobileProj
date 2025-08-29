package com.example.progetto

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

class Home {

    data class User( val username : String, val name : String)

    val upperSection = Color(0xFF3AA0CC)
    val button = Color(0xFF965353)

    @Composable
    fun MenuSection(name: User) {
        /*Scaffold(
            topBar = {}
        {

            }
            
         */

        /*
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(upperSection)
        ) {


        }
        */

    }
    
    
}