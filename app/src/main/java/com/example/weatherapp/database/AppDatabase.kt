package com.example.weatherapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MainModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherDAO(): WeatherDAO?
}