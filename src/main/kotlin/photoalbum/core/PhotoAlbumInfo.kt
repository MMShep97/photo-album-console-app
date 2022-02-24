package photoalbum.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoAlbumInfo(
    @SerialName("id") val photoId: Int,
    val title: String,
)
