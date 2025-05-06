package com.example.assignmentlokal.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.assignmentlokal.local.JobDatabase
import com.example.assignmentlokal.local.entity.JobEntity
import com.example.assignmentlokal.local.entity.KeyEntity
import com.example.assignmentlokal.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class JobMediator @Inject constructor(
    private val apiService: ApiService,
    private val jobDB: JobDatabase
) : RemoteMediator<Int, JobEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, JobEntity>
    ): MediatorResult {
        val page = withContext(Dispatchers.IO) {
            when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    return@withContext null
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    remoteKeys?.nextPage
                }
            }
        } ?: return MediatorResult.Success(endOfPaginationReached = true)

        return try {
            val jobs = apiService.getJobs(page = page)
            val endOfPaginationReached = jobs.response.isEmpty()

            withContext(Dispatchers.IO) {
                jobDB.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        jobDB.jobDao.clearAll()
                        jobDB.keyDao.clearAll()
                    }

                    val keys = jobs.response.map {
                        KeyEntity(
                            it.id,
                            if (page == 1) null else page - 1,
                            if (endOfPaginationReached) null else page + 1
                        )
                    }

                    val jobsEntity = jobs.response.map { it.toEntity() }

                    jobDB.keyDao.insertAll(keys)
                    jobDB.jobDao.insertAll(jobsEntity)
                }
            }

            MediatorResult.Success(endOfPaginationReached)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, JobEntity>): KeyEntity? {
        return state.anchorPosition?.let { position ->
            val job = state.closestItemToPosition(position)
            job?.id?.let { id -> jobDB.keyDao.getById(id) }
        }
    }
    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, JobEntity>): KeyEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { job ->
            jobDB.keyDao.getById(job.id)
        }
    }
}
