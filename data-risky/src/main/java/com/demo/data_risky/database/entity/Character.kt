package com.demo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class Character(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "status") var status: String,
    @ColumnInfo(name = "species") var species: String,
    @ColumnInfo(name = "type") var type: String,
    @ColumnInfo(name = "gender") var gender: String
)
