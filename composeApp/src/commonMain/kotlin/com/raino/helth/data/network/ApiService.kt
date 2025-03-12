package com.raino.helth.data.network

import com.raino.helth.data.di.network.ApiService
import com.raino.helth.data.network.model.ResponseAvidHealth
import com.raino.helth.utiles.NetworkResult
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.Serializable

interface ApiService {
    suspend fun getSampleData(): NetworkResult<ResponseAvidHealth>
}

class ApiServiceImpl(private val client: HttpClient) : ApiService,
    com.raino.helth.data.network.ApiService {
    override suspend fun getSampleData(): NetworkResult<ResponseAvidHealth> {
        return client.get("https://kasra.kasraco.net/lego.web/api/Avid/Health").body()
    }
}
