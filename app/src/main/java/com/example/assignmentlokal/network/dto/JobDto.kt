package com.example.assignmentlokal.network.dto

import com.google.gson.annotations.SerializedName

data class JobDto(
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("primary_details")
    var primaryDetails: PrimaryDetailsDto,
    @SerializedName("salary_max")
    var salaryMax: Int? = null,
    @SerializedName("salary_min")
    var salaryMin: Int? = null,
    @SerializedName("premium_till")
    var premiumTill: String? = null,
    @SerializedName("content")
    var content: String,
    @SerializedName("company_name")
    var companyName: String,
    @SerializedName("shares")
    var shares: Int,
    @SerializedName("whatsapp_no")
    var whatsappNo: String,
    @SerializedName("contact_preference")
    var contactPreference: ContactPreferenceDto,
    @SerializedName("expire_on")
    var expireOn: String? = null,
    @SerializedName("job_hours")
    var jobHours: String,
    @SerializedName("openings_count")
    var openingsCount: Int,
    @SerializedName("job_role")
    var jobRole: String,
    @SerializedName("other_details")
    var otherDetails: String,
    @SerializedName("job_category")
    var jobCategory: String,
)