package photoalbum.core

class PhotoAlbumRunner(
    private val apiClient: ApiClient,
    private val inputHandler: InputHandler,
    private val consoleManager: ConsoleManager,
) {
    suspend fun handleCurrentRun() {
        val albumId = inputHandler.handleUserInput()
        val photoAlbumInfo = apiClient.getPhotoAlbumInfoByAlbumId(albumId)

        printAlbumInfo(photoAlbumInfo)
    }

    private fun printAlbumInfo(photoAlbumInfo: List<PhotoAlbumInfo>) {
        if (photoAlbumInfo.isEmpty()) {
            consoleManager.printMessage("No matching album information")
        } else {
            consoleManager.printMessage("Photo ID | Title")
            consoleManager.printMessage("----------------")
            for (album in photoAlbumInfo) {
                consoleManager.printMessage("[${album.photoId}] ${album.title}")
            }
        }
    }
}
