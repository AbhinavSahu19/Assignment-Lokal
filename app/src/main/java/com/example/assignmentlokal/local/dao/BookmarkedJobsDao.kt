package com.example.assignmentlokal.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.assignmentlokal.local.entity.BookmarkJobEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface BookmarkedJobsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(job: BookmarkJobEntity)

    @Query("DELETE FROM bookmark_table WHERE id = :jobId")
    suspend fun deleteBookmarkById(jobId: Int)

    @Query("SELECT * FROM bookmark_table")
    fun getAllBookmarks(): Flow<List<BookmarkJobEntity>>

    @Query("SELECT * FROM bookmark_table WHERE id = :jobId")
    fun getBookmarkById(jobId: Int): Flow<BookmarkJobEntity>

    @Query("SELECT EXISTS(SELECT 1 FROM bookmark_table WHERE id = :jobId)")
    fun isBookmarked(jobId: Int): Flow<Boolean>
}