package com.example.assignmentlokal.network.dto

import com.example.assignmentlokal.local.entity.ContactPreferenceEmbedded
import com.example.assignmentlokal.local.entity.JobEntity
import com.example.assignmentlokal.local.entity.PrimaryDetailsEmbedded
import com.google.gson.annotations.SerializedName

data class JobDto(
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String?,
    @SerializedName("primary_details")
    var primaryDetails: PrimaryDetailsDto? = PrimaryDetailsDto(),
    @SerializedName("salary_max")
    var salaryMax: Int? = null,
    @SerializedName("salary_min")
    var salaryMin: Int? = null,
    @SerializedName("premium_till")
    var premiumTill: String? = null,
    @SerializedName("content")
    var content: String? = "",
    @SerializedName("company_name")
    var companyName: String? = "",
    @SerializedName("shares")
    var shares: Int? = 0,
    @SerializedName("whatsapp_no")
    var whatsappNo: String? = "",
    @SerializedName("contact_preference")
    var contactPreference: ContactPreferenceDto? = ContactPreferenceDto(),
    @SerializedName("expire_on")
    var expireOn: String? = null,
    @SerializedName("job_hours")
    var jobHours: String? = "",
    @SerializedName("openings_count")
    var openingsCount: Int? = 0,
    @SerializedName("job_role")
    var jobRole: String? = "",
    @SerializedName("other_details")
    var otherDetails: String? = "",
    @SerializedName("job_category")
    var jobCategory: String? = "",
) {
    fun toEntity(): JobEntity {
        return JobEntity(
            id = this.id,
            title = this.title ?: "",
            primaryDetails = this.primaryDetails?.toEmbedded() ?: PrimaryDetailsEmbedded(),
            salaryMax = this.salaryMax,
            salaryMin = this.salaryMin,
            premiumTill = this.premiumTill,
            content = this.content ?: "",
            companyName = this.companyName ?: "",
            shares = this.shares ?: 0,
            whatsappNo = this.whatsappNo ?: "",
            contactPreference = this.contactPreference?.toEmbedded() ?: ContactPreferenceEmbedded(),
            expireOn = this.expireOn,
            jobHours = this.jobHours ?: "",
            openingsCount = this.openingsCount ?: 0,
            jobRole = this.jobRole ?: "",
            otherDetails = this.otherDetails ?: "",
            jobCategory = this.jobCategory ?: "",
        )
    }
}