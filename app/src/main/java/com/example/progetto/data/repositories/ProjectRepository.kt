package com.example.progetto.data.repositories

import com.example.progetto.data.database.ProjDAO
import com.example.progetto.data.database.User
import kotlinx.coroutines.flow.Flow

class ProjectRepository(private val projDAO: ProjDAO) {

    val profile: Flow<List<User>> = projDAO.getAll()

    suspend fun upsert(user: User) = projDAO.upsert(user)

    suspend fun delete(user: User) = projDAO.delete(user)

}