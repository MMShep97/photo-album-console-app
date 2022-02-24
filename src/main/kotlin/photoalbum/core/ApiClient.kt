package photoalbum.core

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

private const val BASE_URL = "https://jsonplaceholder.typicode.com"

class ApiClient(engine: HttpClientEngine = CIO.create()) {
    private val httpClient = HttpClient(engine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getPhotoAlbumInfoByAlbumId(albumId: String): List<PhotoAlbumInfo> =
        httpClient.get("$BASE_URL/photos") { parameter("albumId", albumId) }
}