package com.ranajit.shaadi.data

class NetworkDataProvider(private val apiService: ApiService){
    suspend fun getMatches(noOfResults: Int) = apiService.getMatches(noOfResults)
}