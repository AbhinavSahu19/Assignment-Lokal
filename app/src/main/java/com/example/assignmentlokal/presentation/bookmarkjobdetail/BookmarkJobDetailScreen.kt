package com.example.assignmentlokal.presentation.bookmarkjobdetail

import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.assignmentlokal.presentation.common.formatIsoDateTime

@Composable
fun BookmarkJobDetailScreen(
    viewModel: BookmarkJobDetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val job = viewModel.job.collectAsState().value
    val isBookmarked by viewModel.isBookmarked.collectAsState(initial = false)


    job?.let {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Job Detail",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    },
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    navigationIcon = {
                        IconButton(onClick = { navigateBack() }) {
                            Icon(
                                Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            if (isBookmarked) {
                                viewModel.removeBookmark(job.id) // Remove from bookmarks by id
                            } else {
                                viewModel.addBookmark(job.toJobEntity()) // Add to bookmarks
                            }
                        }) {
                            Icon(
                                imageVector = if (isBookmarked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                                contentDescription = "Bookmark"
                            )
                        }
                    }
                )
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                item {
                    Column {
                        Text(
                            text = it.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        InfoRow(label = "Company", value = it.companyName)
                        InfoRow(label = "Location", value = it.primaryDetails.place)
                        InfoRow(label = "Job Role", value = it.jobRole)
                        InfoRow(label = "Category", value = it.jobCategory)
                        InfoRow(
                            label = "Salary",
                            value = "₹${it.salaryMin ?: "N/A"} - ₹${it.salaryMax ?: "N/A"}"
                        )
                        InfoRow(label = "Phone", value = it.whatsappNo)
                        InfoRow(label = "Experience", value = it.primaryDetails.experience)
                        InfoRow(label = "Qualification", value = it.primaryDetails.qualification)
                        InfoRow(label = "Job Type", value = it.primaryDetails.jobType)
                        InfoRow(label = "Openings", value = it.openingsCount.toString())
                        InfoRow(label = "Job Hours", value = it.jobHours)
                        Text(
                            text = "Premium till: ${formatIsoDateTime(job.premiumTill ?: "")}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Expires On: ${formatIsoDateTime(job.expireOn ?: "")}",
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Spacer(modifier = Modifier.height(16.dp))

//                        if (it.content.isNotBlank()) {
//                            Text(
//                                text = "Job Description",
//                                fontWeight = FontWeight.SemiBold,
//                                fontSize = 18.sp
//                            )
//                            Spacer(modifier = Modifier.height(8.dp))
////                            UnicodeDecodedText(unicodeText = it.content)
//                            JobWebView(content = it.content, modifier = Modifier.fillMaxWidth())
//                        }
                    }
                }
            }
        }
    } ?: run {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = "$label: ",
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 16.sp
        )
        Text(
            text = value,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 14.sp
        )
    }
}


@Composable
fun JobWebView(content: String, modifier: Modifier) {
    AndroidView(
        modifier = modifier
            .padding(10.dp, 2.dp, 10.dp, 5.dp)
            .background(color = androidx.compose.ui.graphics.Color.White),
        factory = { context ->
            TextView(context).apply {
                setTextColor(Color.Black.value.toInt())
                textSize = 16f
                setLinkTextColor(Color.Blue.value.toInt())
                movementMethod = LinkMovementMethod.getInstance()
            }
        },
        update = { it.text = HtmlCompat.fromHtml(content, HtmlCompat.FROM_HTML_MODE_COMPACT) }
    )
}

@Composable
fun UnicodeDecodedText(unicodeText: String) {
    AndroidView(
        factory = { context ->
            TextView(context).apply {
                textSize = 16f
                setTextIsSelectable(true)
                text = HtmlCompat.fromHtml(
                    unicodeText,
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )
                setPadding(0, 0, 0, 24)
            }
        },
        update = {
            it.text = HtmlCompat.fromHtml(
                unicodeText,
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
}