package org.example.pvproyecto

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform