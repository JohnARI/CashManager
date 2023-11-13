package org.moulamanager.client

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform