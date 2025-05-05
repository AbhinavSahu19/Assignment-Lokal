package com.example.assignmentlokal.network.dto

import com.google.gson.annotations.SerializedName


data class PrimaryDetailsDto(

    @SerializedName("Place") var place: String? = null,
    @SerializedName("Salary") var salary: String? = null,
    @SerializedName("Job_Type") var jobType: String? = null,
    @SerializedName("Experience") var experience: String? = null,
    @SerializedName("Fees_Charged") var feesCharged: String? = null,
    @SerializedName("Qualification") var qualification: String? = null

)