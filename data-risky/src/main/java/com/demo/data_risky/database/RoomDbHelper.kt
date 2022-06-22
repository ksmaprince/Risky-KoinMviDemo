package com.demo.data_risky.database

import android.content.Context
import androidx.room.Room
import com.demo.data_risky.database.dao.CharacterDao

class RoomDbHelper(private val context: Context) {
    private val characterDao: CharacterDao

    init {
        val rxDatabase: RxDatabase = Room.databaseBuilder(
            context, RxDatabase::class.java,
            "risky_db"
        )
            .fallbackToDestructiveMigration()
            .build()

        characterDao = rxDatabase.characterDao()
    }

    fun getCharacterDao(): CharacterDao = characterDao
}