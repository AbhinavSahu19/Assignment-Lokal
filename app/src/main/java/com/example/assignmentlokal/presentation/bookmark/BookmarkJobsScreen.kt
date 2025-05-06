package com.example.assignmentlokal.presentation.bookmark

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.assignmentlokal.presentation.common.GeneralTopBar
import com.example.assignmentlokal.presentation.jobs.JobItemCard

@Composable
fun BookmarkJobsScreen(
    viewModel: BookmarkViewModel = hiltViewModel(), // or provide via DI
    onJobClick: (Int) -> Unit
) {
    val bookmarkedJobs by viewModel.bookmarkedJobs.collectAsState()

    Scaffold(
        topBar = {
            GeneralTopBar("Bookmarked Jobs")
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            items(bookmarkedJobs) { job ->
                JobItemCard(
                    job.title,
                    job.primaryDetails.place,
                    job.salaryMin,
                    job.salaryMax,
                    job.whatsappNo
                ) { onJobClick(job.id) }
            }
        }
    }
}
