package com.example.assignmentlokal.presentation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun BottomNavigationBar(
    navController: NavHostController
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.primaryContainer
    ) {
        val routeColor =
            if (navController.currentDestination?.route == Screen.Jobs.route)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.onPrimaryContainer
        val bookmarkColor =
            if (navController.currentDestination?.route == Screen.Bookmarks.route)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.onPrimaryContainer
        BottomNavigationItem(
            selected = navController.currentDestination?.route == Screen.Jobs.route,
            onClick = { navController.navigate(Screen.Jobs.route) },
            icon = {
                Icon(
                    Icons.Default.Home,
                    contentDescription = "Jobs",
                    tint = routeColor
                )
            },
            label = { Text("Jobs") },
            selectedContentColor = MaterialTheme.colorScheme.primary,
            unselectedContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
        BottomNavigationItem(
            selected = navController.currentDestination?.route == Screen.Bookmarks.route,
            onClick = { navController.navigate(Screen.Bookmarks.route) },
            icon = {
                Icon(
                    Icons.Default.Star,
                    contentDescription = "Bookmarks",
                    tint = bookmarkColor
                )
            },
            label = { Text("Bookmarks") },
            selectedContentColor = MaterialTheme.colorScheme.primary,
            unselectedContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        )
    }
}