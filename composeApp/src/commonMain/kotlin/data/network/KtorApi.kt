package data.network

import io.ktor.client.HttpClient


private const val BASE_URL = "https://api.github.com"
abstract class KtorApi {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
}