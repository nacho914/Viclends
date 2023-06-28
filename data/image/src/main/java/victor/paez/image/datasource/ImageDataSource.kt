package victor.paez.image.datasource

import android.net.Uri

interface ImageDataSource {
    suspend fun uploadImage(uriImg: Uri): String
}
