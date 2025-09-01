package com.example.progetto.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow
@Dao
interface ProjDAO{
    //includi tutte le funzioni e query

    @Insert
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user WHERE username = :username")
     suspend fun getUserByUsername(username: String): User?

    @Query("SELECT * FROM User WHERE id = :userId")
    suspend fun getUserById(userId: Int): User?

    @Query("SELECT  * FROM Booking WHERE idB = :bookingId")
    suspend fun getBookingInfo(bookingId: Int): Booking?

    @Query("SELECT idBooking FROM Booking WHERE userId = :userId")
    suspend fun getBookingFromUser(userId : Int): List<Int>

    @Insert
    suspend fun addBooking(booking: Booking)

    @Upsert
    suspend fun upsert(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM user")
    fun getAll(): Flow<List<User>>

    @Query("SELECT * from Event where idEvent = :idEvent")
    suspend fun getEvent(idEvent: Int): Event?

    /**/
    @Query("SELECT * FROM Register WHERE idUser = :userId")
    suspend fun getRegisterByUserId(userId: Int): Register?

    @Query("SELECT * FROM Booking WHERE userId= :userId")
    suspend fun getBookingForUser(userId: Int):Booking?

    @Query("DELETE FROM Booking WHERE idBooking = :idBooking")
    suspend fun deleteBooking(idBooking: Int)

   /* @Query("SELECT userImg FROM user WHERE id = :userId")
    fun getUserImg(userId: Int)

    @Query("SELECT username FROM user WHERE id = :userId")
    fun getUsernameFromId(userId: Int)

    */










}