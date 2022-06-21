package com.demo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.data.database.entity.Character
import io.reactivex.Single

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entities: List<Character>)

    @Query("SELECT * FROM character ORDER BY id ASC")
    fun getAllCharacters(): Single<List<Character>>

    @Query("SELECT COUNT(*) from character")
    fun getCharacterCount(): Single<Int>


    @Query("DELETE from character")
    fun clearAll()
}