package com.example.assignmentlokal.repository

import androidx.paging.Pager
import com.example.assignmentlokal.local.entity.BookmarkJobEntity
import com.example.assignmentlokal.local.entity.JobEntity
import kotlinx.coroutines.flow.Flow

interface JobRepository {
    fun getAllJobs(): Pager<Int, JobEntity>
    suspend fun getById(id: Int): Flow<JobEntity>

    suspend fun getAllBookmarkedJobs(): Flow<List<BookmarkJobEntity>>
    suspend fun getBookmarkedJobById(id: Int): Flow<BookmarkJobEntity>

    suspend fun addBookmark(job: BookmarkJobEntity)
    suspend fun removeBookmark(jobId: Int)
    fun isJobBookmarked(jobId: Int): Flow<Boolean>
}