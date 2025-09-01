package com.example.progetto.data.database

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val username: String,
    @ColumnInfo val userImg: String?,
    @ColumnInfo val passwordHash: String,
    @ColumnInfo val name: String,
    @ColumnInfo val surname: String,
    @ColumnInfo val email: String,//Memorizza l'hash della password
    //val salt: String? = null,//stringa da codificare
    //val iterations: Int? = null//numero di iterazioni usate per l'hashing
)

@Entity
data class Boat(
    @PrimaryKey val idBoat: Int,
    @ColumnInfo val description: String,
    @ColumnInfo val name: String,
    @ColumnInfo val type: String,
    @ColumnInfo val capienza: Int, //dopo cerca in inglese capienza
)

@Entity(
    foreignKeys = [ForeignKey(
        entity = Boat::class,
        parentColumns = ["idBoat"],
        childColumns = ["idB"],
        onDelete = ForeignKey.CASCADE
    ),
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["idB"]), Index(value = ["userId"])]
)
data class Booking(
    @PrimaryKey(autoGenerate = true) val idBooking: Int=0,
    @ColumnInfo val date: String,
    @ColumnInfo val userId: Int,
    @ColumnInfo val idB: Int,
    @ColumnInfo val number: Int,
    //@ColumnInfo val time:
    //durata -> se servirà
)

@Entity
data class Event(
    @PrimaryKey val idEvent: Int,
    @ColumnInfo val date: String,
    @ColumnInfo val description: String,
    @ColumnInfo val eventImg: String,
)

@Entity(
    foreignKeys = [ForeignKey(
    entity = User::class,
    parentColumns = ["id"],
    childColumns = ["idUser"],
    onDelete = ForeignKey.CASCADE
)]
)
data class Register(
    @PrimaryKey val idUser: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val surname: String,
    @ColumnInfo val email: String,
    //@ColumnInfo val birthDate:Long? irrilevante a meno che non richiesto

)



