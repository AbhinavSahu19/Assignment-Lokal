package com.example.assignmentlokal.network

import com.example.assignmentlokal.network.dto.Results
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("jobs")
    suspend fun getJobs(@Query("page") page: Int): Results
}