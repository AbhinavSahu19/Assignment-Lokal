package com.example.assignmentlokal.presentation.jobs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun JobItemCard(
    title: String,
    place: String,
    salaryMin: Int?,
    salaryMax: Int?,
    whatsappNo: String,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = 4.dp,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        shape = RoundedCornerShape(5.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)) {
                        append("Location: ")
                    }
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Medium, fontSize = 14.sp)) {
                        append(place)
                    }
                },
                modifier = Modifier.padding(bottom = 2.dp)
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)) {
                        append("Salary: ")
                    }
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Medium, fontSize = 14.sp)) {
                        append("₹${salaryMin ?: "N/A"} - ₹${salaryMax ?: "N/A"}")
                    }
                },
                modifier = Modifier.padding(bottom = 2.dp)
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)) {
                        append("Phone: ")
                    }
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Medium, fontSize = 14.sp)) {
                        append(whatsappNo)
                    }
                },
                modifier = Modifier.padding(bottom = 2.dp)
            )
        }
    }
}

@Composable
@Preview
fun JobItemCardPre() {
    JobItemCard(
        "Satyam Home Care Services wants nannies and ward boys for patient care, housework and cooking.",
        "Indore",
        30000,
        30000,
        "12331232131",
        {}
    )
}