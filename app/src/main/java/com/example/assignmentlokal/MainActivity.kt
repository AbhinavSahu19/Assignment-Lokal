package com.example.assignmentlokal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.assignmentlokal.presentation.MainApp
import com.example.assignmentlokal.ui.theme.AssignmentLokalTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssignmentLokalTheme {
                MainApp()
            }
        }
    }
}