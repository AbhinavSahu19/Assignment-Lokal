package com.example.assignmentlokal.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "job_table")
data class JobEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    var title: String,
    @Embedded var primaryDetails: PrimaryDetailsEmbedded,
    var salaryMax: Int?,
    var salaryMin: Int?,
    var premiumTill: String?,
    var content: String,
    var companyName: String,
    var shares: Int,
    var whatsappNo: String,
    @Embedded var contactPreference: ContactPreferenceEmbedded,
    var expireOn: String?,
    var jobHours: String,
    var openingsCount: Int,
    var jobRole: String,
    var otherDetails: String,
    var jobCategory: String,
)
