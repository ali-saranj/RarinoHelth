package com.raino.helth.data.network

import com.raino.helth.data.network.model.ResponseAvidHealth
import com.raino.helth.utiles.NetworkResult
import com.raino.helth.utiles.toResult
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

interface ApiService {
    suspend fun getSampleData(): NetworkResult<ResponseAvidHealth>
}

class ApiServiceImpl(private val client: HttpClient) : ApiService {

    override suspend fun getSampleData(): NetworkResult<ResponseAvidHealth> {
        return client.get(urlString = "https://kasra.kasraco.net/lego.web/api/Avid/Health").toResult<ResponseAvidHealth>()
    }
}
