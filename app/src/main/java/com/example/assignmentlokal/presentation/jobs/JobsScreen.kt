package com.example.assignmentlokal.presentation.jobs

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.assignmentlokal.presentation.common.GeneralTopBar
import com.example.assignmentlokal.presentation.common.LoadingAnimation

@Composable
fun JobsScreen(
    homeViewModel: JobsViewModel = hiltViewModel(),
    navigateToJobDetail: (Int) -> Unit
) {
    val jobs = homeViewModel.allJobs.collectAsLazyPagingItems()


    Scaffold(
        topBar = {
            GeneralTopBar("Jobs")
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(jobs.itemCount) { index ->
                jobs[index]?.let { job ->
                    JobItemCard(
                        title = job.title,
                        place = job.primaryDetails.place,
                        salaryMin = job.salaryMin,
                        salaryMax = job.salaryMax,
                        whatsappNo = job.whatsappNo
                    ) {
                        navigateToJobDetail(job.id)
                    }
                }
            }

            jobs.apply {
                when (loadState.refresh) {
                    is LoadState.Loading -> {
                        item {
                            LoadingAnimation(Modifier.padding(16.dp).fillMaxSize())
                        }
                    }

                    is LoadState.Error -> {
                        item {
                            Text(
                                "Error loading more jobs",
                                color = Color.Red,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }

                    else -> {}
                }


                when (loadState.append) {
                    is LoadState.Loading -> {
                        item {
                            LoadingAnimation(Modifier.padding(16.dp).fillMaxSize())
                        }
                    }

                    is LoadState.Error -> {
                        item {
                            Text(
                                "Error loading more jobs",
                                color = Color.Red,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }

                    else -> {}
                }
            }
        }
    }
}

