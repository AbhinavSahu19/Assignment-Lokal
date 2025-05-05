package com.example.assignmentlokal.network.dto

import com.google.gson.annotations.SerializedName

data class JobDto(
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("type")
    var type: Int? = null,
    @SerializedName("primary_details")
    var primaryDetails: PrimaryDetailsDto? = PrimaryDetailsDto(),
    @SerializedName("salary_max")
    var salaryMax: Int? = null,
    @SerializedName("salary_min")
    var salaryMin: Int? = null,
    @SerializedName("premium_till")
    var premiumTill: String? = null,
    @SerializedName("content")
    var content: String? = null,
    @SerializedName("company_name")
    var companyName: String? = null,
    @SerializedName("shares")
    var shares: Int? = null,
    @SerializedName("fb_shares")
    var fbShares: Int? = null,
    @SerializedName("whatsapp_no")
    var whatsappNo: String? = null,
    @SerializedName("contact_preference")
    var contactPreference: ContactPreferenceDto? = ContactPreferenceDto(),
    @SerializedName("expire_on")
    var expireOn: String? = null,
    @SerializedName("job_hours")
    var jobHours: String? = null,
    @SerializedName("openings_count")
    var openingsCount: Int? = null,
    @SerializedName("job_role")
    var jobRole: String? = null,
    @SerializedName("other_details")
    var otherDetails: String? = null,
    @SerializedName("job_category")
    var jobCategory: String? = null,
)