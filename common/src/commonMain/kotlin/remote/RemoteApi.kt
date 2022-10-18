package remote

import io.ktor.client.*

expect val ktorClient: HttpClient

interface RemoteApi {
    val client get() = ktorClient
}