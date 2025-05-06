package com.example.assignmentlokal.repository


import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.assignmentlokal.local.JobDatabase
import com.example.assignmentlokal.local.entity.BookmarkJobEntity
import com.example.assignmentlokal.local.entity.JobEntity
import com.example.assignmentlokal.mediator.JobMediator
import com.example.assignmentlokal.network.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class JobRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val roomDB: JobDatabase
) : JobRepository {

    override fun getAllJobs(): Pager<Int, JobEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            remoteMediator = JobMediator(apiService, roomDB),
            pagingSourceFactory = { roomDB.jobDao.getAll() }
        )
    }

    override suspend fun getById(id: Int): Flow<JobEntity> = roomDB.jobDao.getById(id)

    override suspend fun getAllBookmarkedJobs(): Flow<List<BookmarkJobEntity>> = roomDB.bookmarkDao.getAllBookmarks()

    override suspend fun getBookmarkedJobById(id: Int): Flow<BookmarkJobEntity> = roomDB.bookmarkDao.getBookmarkById(id)

    override suspend fun addBookmark(job: BookmarkJobEntity) {
        roomDB.bookmarkDao.insertBookmark(job)
    }

    override suspend fun removeBookmark(jobId: Int) {
        roomDB.bookmarkDao.deleteBookmarkById(jobId)
    }

    override fun isJobBookmarked(jobId: Int): Flow<Boolean> {
        return roomDB.bookmarkDao.isBookmarked(jobId)
    }
}