package com.raino.helth.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ResponseAvidHealth(
    @SerialName("connectionStatus")
    val connectionStatus: Boolean,
    @SerialName("health")
    val health: Boolean,
    @SerialName("isHTTPS")
    val isHTTPS: Boolean
)