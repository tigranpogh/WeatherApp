package com.example.weatherapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dao_model")
data class MainModel (
    @PrimaryKey
    @ColumnInfo(name = "day") val day: String,
    @ColumnInfo(name = "temp") val temp: String,
    @ColumnInfo(name = "city") val city: String
)