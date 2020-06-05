package com.example.redbook.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.redbook.data.dao.AnimalDao
import com.example.redbook.data.model.Animal

@Database(entities = [Animal::class],version = 1)
abstract class RedBookDatabase : RoomDatabase() {
    companion object{

        @Volatile
        private var INSTANCE: RedBookDatabase? = null

        fun getInstance(context: Context): RedBookDatabase{
            return INSTANCE ?: synchronized(this){
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): RedBookDatabase{
            return Room.databaseBuilder(context, RedBookDatabase::class.java,
                "book-database.db")
                .createFromAsset("book-database.db")
                .allowMainThreadQueries()
                .build()
        }
    }
    abstract fun data() : AnimalDao
}