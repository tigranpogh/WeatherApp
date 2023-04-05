package com.example.weatherapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WeatherDAO {
    @get:Query("SELECT * FROM dao_model")
    val all: LiveData<List<MainModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(mainModel: MainModel?)

    @Query("delete from dao_model")
    suspend fun deleteAll()
}