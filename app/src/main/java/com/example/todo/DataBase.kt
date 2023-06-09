package com.example.todo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [TableTask::class], version = 1)
abstract class DataBase : RoomDatabase() {

    abstract fun getDao(): Dao

    companion object{
        fun getDataBase(context: Context): DataBase{
            return Room.databaseBuilder(
                context.applicationContext,
                DataBase::class.java,
                "DataBase.db"
            ).build()
        }
    }
}