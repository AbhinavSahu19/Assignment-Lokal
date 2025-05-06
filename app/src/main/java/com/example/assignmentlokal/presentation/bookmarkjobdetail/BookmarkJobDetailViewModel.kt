package com.example.assignmentlokal.presentation.bookmarkjobdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignmentlokal.local.entity.BookmarkJobEntity
import com.example.assignmentlokal.local.entity.JobEntity
import com.example.assignmentlokal.repository.JobRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BookmarkJobDetailViewModel @Inject constructor(
    private val repo: JobRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val jobId: Int = checkNotNull(savedStateHandle["jobId"])

    private val _job = MutableStateFlow<BookmarkJobEntity?>(null)
    val job: StateFlow<BookmarkJobEntity?> = _job.asStateFlow()

    private val _isBookmarked = MutableStateFlow<Boolean>(false)
    val isBookmarked: StateFlow<Boolean> get() = _isBookmarked
    init {
        getJob()
        checkIfBookmarked(jobId)
    }

    private fun getJob() {
        viewModelScope.launch {
           repo.getBookmarkedJobById(jobId).collect {
                _job.value = it
            }
        }
    }

    fun addBookmark(job: JobEntity) {
        viewModelScope.launch {
            val bookmarkEntity = BookmarkJobEntity(
                id = job.id,
                title = job.title,
                primaryDetails = job.primaryDetails,
                salaryMax = job.salaryMax,
                salaryMin = job.salaryMin,
                premiumTill = job.premiumTill,
                content = job.content,
                companyName = job.companyName,
                shares = job.shares,
                whatsappNo = job.whatsappNo,
                contactPreference = job.contactPreference,
                expireOn = job.expireOn,
                jobHours = job.jobHours,
                openingsCount = job.openingsCount,
                jobRole = job.jobRole,
                otherDetails = job.otherDetails,
                jobCategory = job.jobCategory
            )
            repo.addBookmark(bookmarkEntity)
            _isBookmarked.value = true
        }
    }

    fun removeBookmark(jobId: Int) {
        viewModelScope.launch {
            repo.removeBookmark(jobId)
            _isBookmarked.value = false
        }
    }

    fun checkIfBookmarked(jobId: Int) {
        viewModelScope.launch {
            repo.isJobBookmarked(jobId).collect { isBookmarked ->
                _isBookmarked.value = isBookmarked
            }
        }
    }
}