package com.example.redbook.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.redbook.data.model.Animal

@Dao
interface AnimalDao {
    @Query("Select * From book WHERE type = :type ")
    fun getAnimals(type: Int): List<Animal>

    @Query("Select * From book Where id = :id")
    fun getAnimalById(id: Int): Animal

    @Query("Select * From book Where type=:type and nameEng like:word")
    fun searchAnimalByName(type: Int, word: String): List<Animal>

    @Query("Select * From book Where isFavorite=1")
    fun getFavorite(): List<Animal>

    @Update
    fun updateAnimal(animal: Animal)
}