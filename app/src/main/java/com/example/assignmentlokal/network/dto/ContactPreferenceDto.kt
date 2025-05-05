package com.example.assignmentlokal.network.dto

import com.google.gson.annotations.SerializedName

data class ContactPreferenceDto(

    @SerializedName("preference")
    var preference: Int,
    @SerializedName("whatsapp_link")
    var whatsappLink: String,
    @SerializedName("preferred_call_start_time")
    var preferredCallStartTime: String,
    @SerializedName("preferred_call_end_time")
    var preferredCallEndTime: String

)