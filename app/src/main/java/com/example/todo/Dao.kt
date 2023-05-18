package com.example.todo

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@androidx.room.Dao
interface Dao {
    @Insert
    fun insertTask(tableTask: TableTask)

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): List<TableTask>

    @Query("SELECT * FROM tasks WHERE id = :id")
    fun getTask(id: Int): TableTask

//    @Query("DELETE FROM tasks WHERE `title` = :title")
//    fun myDelete(title: String): TableTask

    @Delete
    fun deleteTask(tableTask: TableTask)
}