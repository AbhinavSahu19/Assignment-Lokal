package com.example.assignmentlokal.presentation.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignmentlokal.local.entity.BookmarkJobEntity
import com.example.assignmentlokal.repository.JobRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BookmarkViewModel @Inject constructor(
    val repository: JobRepository
) : ViewModel() {

    private val _bookmarkedJobs = MutableStateFlow<List<BookmarkJobEntity>>(emptyList())
    val bookmarkedJobs: StateFlow<List<BookmarkJobEntity>> = _bookmarkedJobs

    init {
        fetchBookmarks()
    }

    private fun fetchBookmarks() {
        viewModelScope.launch {
            repository.getAllBookmarkedJobs().collect { jobs ->
                _bookmarkedJobs.value = jobs
            }
        }
    }
}
