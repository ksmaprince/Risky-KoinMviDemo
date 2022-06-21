package com.demo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.data.database.dao.CharacterDao
import com.demo.data.database.entity.Character

@Database(entities = [Character::class], version = 1)
abstract class RxDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}