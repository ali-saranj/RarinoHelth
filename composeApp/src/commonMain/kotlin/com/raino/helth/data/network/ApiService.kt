package com.raino.helth.data.network

import com.raino.helth.data.di.network.ApiService
import com.raino.helth.data.di.network.SampleResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.Serializable

interface ApiService {
    suspend fun getSampleData(): com.raino.helth.data.network.SampleResponse
}

class ApiServiceImpl(private val client: HttpClient) : ApiService {
    override suspend fun getSampleData(): SampleResponse {
        return client.get("https://api.example.com/data").body()
    }
}

@Serializable
data class SampleResponse(
    val message: String,
    val status: String
) 