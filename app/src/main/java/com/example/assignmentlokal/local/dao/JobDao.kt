package com.example.assignmentlokal.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.assignmentlokal.local.entity.JobEntity


@Dao
interface JobDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(blogs: List<JobEntity>)

    @Query("SELECT * FROM job_table ORDER BY id desc")
    fun getAll(): PagingSource<Int, JobEntity>

    @Query("DELETE FROM job_table")
    suspend fun clearAll()
}