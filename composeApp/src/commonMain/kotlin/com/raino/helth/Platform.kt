package com.raino.helth

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform