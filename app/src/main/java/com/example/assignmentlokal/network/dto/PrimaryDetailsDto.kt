package com.example.assignmentlokal.network.dto

import com.example.assignmentlokal.local.entity.PrimaryDetailsEmbedded
import com.google.gson.annotations.SerializedName


data class PrimaryDetailsDto(

    @SerializedName("Place") var place: String,
    @SerializedName("Salary") var salary: String,
    @SerializedName("Job_Type") var jobType: String,
    @SerializedName("Experience") var experience: String,
    @SerializedName("Fees_Charged") var feesCharged: String,
    @SerializedName("Qualification") var qualification: String

) {
    fun toEmbedded(): PrimaryDetailsEmbedded {
        return PrimaryDetailsEmbedded(
            place = place,
            salary = salary,
            jobType = jobType,
            experience = experience,
            feesCharged = feesCharged,
            qualification = qualification
        )
    }
}