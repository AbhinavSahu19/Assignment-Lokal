package com.example.assignmentlokal.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.assignmentlokal.presentation.bookmark.BookmarkJobsScreen
import com.example.assignmentlokal.presentation.bookmarkjobdetail.BookmarkJobDetailScreen
import com.example.assignmentlokal.presentation.home.JobsScreen
import com.example.assignmentlokal.presentation.jobdetail.JobDetailScreen

@Composable
fun MainApp(
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentDestination in listOf("jobs", "bookmarks")) {
                BottomNavigationBar(navController)
            }
        }
    ) { paddingValues ->
        NavHost(
            navController,
            startDestination = Screen.Jobs.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Jobs.route) {
                JobsScreen(
                    navigateToJobDetail = { jobId ->
                        navController.navigate(Screen.JobDetail.createRoute(jobId))
                    }
                )
            }
            composable(Screen.Bookmarks.route) {
                BookmarkJobsScreen(
                    onJobClick = { jobId ->
                        navController.navigate(Screen.BookmarkJobDetail.createRoute(jobId))
                    }
                )
            }
            composable(
                route = Screen.JobDetail.route,
                arguments = listOf(navArgument("jobId") { type = NavType.IntType })
            ) { backStackEntry ->
                val jobId = backStackEntry.arguments?.getInt("jobId") ?: return@composable
                JobDetailScreen(navigateBack = { navController.popBackStack() })
            }
            composable(
                route = Screen.BookmarkJobDetail.route,
                arguments = listOf(navArgument("jobId") { type = NavType.IntType })
            ) { backStackEntry ->
                val jobId = backStackEntry.arguments?.getInt("jobId") ?: return@composable
                BookmarkJobDetailScreen(navigateBack = { navController.popBackStack() })
            }
        }
    }
}
