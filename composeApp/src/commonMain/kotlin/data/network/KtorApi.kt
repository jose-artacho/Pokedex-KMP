package data.network

import data.network.PokemonServiceConstants.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.path
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class KtorApi(httpClientEngine: HttpClientEngine) {

    val client = HttpClient(httpClientEngine) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend inline fun <reified T> getApiCall(
        url: String,
        params: Map<String, String> = emptyMap()
    ): Result<T> =
        try {
            val response = client.get {
                pathUrl(url)
                params.forEach { (key, value) -> parameter(key, value) }
            }
            Result.success(response.body<T>())
        } catch (exception: ResponseException) {
            Result.failure(exception)
        } catch (exception: Throwable) {
            Result.failure(exception)
        }

    fun HttpRequestBuilder.pathUrl(path: String) {
        url {
            takeFrom(BASE_URL)
            path(path)
        }
    }
}