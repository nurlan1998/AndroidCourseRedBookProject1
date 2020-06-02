package com.example.redbook.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.redbook.data.model.Animal

@Dao
interface AnimalDao {
    @Query("Select * From book WHERE type = :type ")
    fun getAnimals(type: Int) : List<Animal>
}