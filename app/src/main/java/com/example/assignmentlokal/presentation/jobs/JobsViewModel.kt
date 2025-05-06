package com.example.assignmentlokal.presentation.jobs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.assignmentlokal.repository.JobRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JobsViewModel @Inject constructor(
    repo: JobRepository
) : ViewModel() {
    val allJobs = repo.getAllJobs().flow.cachedIn(viewModelScope)
}
