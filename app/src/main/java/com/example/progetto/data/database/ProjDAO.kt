package com.example.progetto.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

interface ProjDAO{
    //includi tutte le funzioni e query

    @Insert
    fun addUser(user: User)

    @Query("SELECT * FROM user WHERE username = :username")
    suspend fun getUserByUsername(username: String): User?

    @Query("SELECT  * FROM Booking WHERE idB = :bookingId")
    fun getBookingInfo(bookingId: Int): Booking?

    @Query("SELECT idBooking FROM Booking WHERE userId = :userId")
    fun getBookingFromUser(userId : Int): List<Int>

    @Insert
    fun addBooking(bucchin: Booking)

    @Upsert
    suspend fun upsert(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM user")
    fun getAll(): Flow<List<User>>

    @Query("SELECT * from Event where idEvent = :idEvent")
    fun getEvent(idEvent: Event)

    /**/
    @Query("SELECT * FROM Register WHERE idUser = :userId")
    suspend fun getRegisterByUserId(userId: Int): Register?







}