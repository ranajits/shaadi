package com.ranajit.shaadi.data

import com.ranajit.shaadi.model.MatchesData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/?")
    suspend fun getMatches(@Query("results") noOfResults: Int): MatchesData
}