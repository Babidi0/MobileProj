package com.example.progetto.data.database

import androidx.room.*
import androidx.room.Database


@Database(entities = [User::class, Boat::class, Booking::class, Event::class,Register::class], version = 2)
    abstract class ProjectDatabase : RoomDatabase() {
        abstract fun DAO() : ProjDAO

        companion object {
            @Volatile
            private var instance: ProjectDatabase? = null

            fun getDatabase(ctx: android.content.Context) =
                instance ?: synchronized(this) {
                    instance = Room.databaseBuilder(
                        ctx,
                        ProjectDatabase::class.java,
                        "Project"
                    ).fallbackToDestructiveMigration()
                        .build()
                    instance
                }
        }
    }

