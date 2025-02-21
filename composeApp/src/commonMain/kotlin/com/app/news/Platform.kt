package com.app.news

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform