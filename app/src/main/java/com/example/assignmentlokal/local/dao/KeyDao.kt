package com.example.assignmentlokal.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.assignmentlokal.local.entity.KeyEntity

@Dao
interface KeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(keys: List<KeyEntity>)

    @Query("SELECT * FROM key_table where id = :id")
    fun getById(id : Int): KeyEntity?

    @Query("DELETE FROM key_table ")
    suspend fun clearAll()
}