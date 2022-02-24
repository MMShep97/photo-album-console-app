package photoalbum

import io.ktor.client.engine.mock.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import photoalbum.core.ApiClient
import photoalbum.core.PhotoAlbumInfo

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ApiClientTest {
    private val validAlbumId = "1"
    private val deserializedResponse = listOf(PhotoAlbumInfo(1, "fake title 1"))

    private val mockEngine = MockEngine { request ->
            if (request.url.toString().endsWith("/photos?albumId=$validAlbumId")) {
                respond(
                    content = Json.encodeToString(deserializedResponse),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            } else {
                error("Unhandled path: ${request.url.encodedPath}")
            }
        }

    private val apiClient = ApiClient(mockEngine)

    @Test
    fun `should fetch photo album info properly`() {
        val response = runBlocking { apiClient.getPhotoAlbumInfoByAlbumId(validAlbumId) }

        assertEquals(deserializedResponse, response)
    }
}