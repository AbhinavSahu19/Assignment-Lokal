package com.example.assignmentlokal.presentation


sealed class Screen(val route: String) {
    object Jobs : Screen("jobs")
    object Bookmarks : Screen("bookmarks")
    object JobDetail : Screen("jobDetail/{jobId}") {
        fun createRoute(jobId: Int) = "jobDetail/$jobId"
    }
    object BookmarkJobDetail : Screen("bookmarkJobDetail/{jobId}") {
        fun createRoute(jobId: Int) = "jobDetail/$jobId"
    }
}
