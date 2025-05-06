package com.example.assignmentlokal.presentation.common

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun formatIsoDateTime(isoString: String): String {
    return try {
        val zonedDateTime = OffsetDateTime.parse(isoString)
        val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a", Locale.getDefault())
        zonedDateTime.format(formatter)
    } catch (e: Exception) {
        ""
    }
}