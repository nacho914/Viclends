package victor.paez.image.repository

import android.net.Uri

interface ImageRepository {
    suspend fun uploadImage(uriImg: Uri): String
}
