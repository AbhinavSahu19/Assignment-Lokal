package com.example.assignmentlokal.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun GeneralTopBar(
    heading: String? = null
){
    Column (
        modifier = Modifier.statusBarsPadding()
    ){
        if(heading != null){
            TopAppBar(
                title = {
                    Text(
                        text = "Jobs",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                backgroundColor = MaterialTheme.colorScheme.primaryContainer
            )
        }
    }
}
