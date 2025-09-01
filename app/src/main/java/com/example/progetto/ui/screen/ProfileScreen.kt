@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.progetto.ui.screen



import TopBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.progetto.data.database.ProjDAO
import com.example.progetto.data.repositories.ProjectRepository
import com.example.progetto.ui.Composable.BottomBar


@Composable
fun ProfileScreen( navController: NavHostController, viewModel: UserViewModel, dao: ProjDAO, repository: ProjectRepository) {

    val sessionUser by repository.sessionUser.collectAsState(initial = null)

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        item {
            Box(
                Modifier.size(120.dp).clip(CircleShape).background(Color.Gray)
            )
            Spacer(Modifier.height(16.dp))
            Text("Nome Utente")
            Spacer(Modifier.height(24.dp))
            Text("Prenotazioni Effettuate", style = MaterialTheme.typography.titleMedium)
        }
    }
}