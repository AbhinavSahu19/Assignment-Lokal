package com.example.assignmentlokal.network.dto

import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("results")
    var response : List<JobDto>
)
