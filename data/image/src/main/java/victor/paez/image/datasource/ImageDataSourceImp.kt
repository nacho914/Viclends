package victor.paez.image.datasource

import android.net.Uri
import com.google.firebase.storage.StorageReference
import victor.paez.image.STORAGE_CLIENT_IMAGE
import java.util.UUID
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ImageDataSourceImp@Inject constructor(
    private val storageReference: StorageReference,
) : ImageDataSource {
    override suspend fun uploadImage(uriImg: Uri): String {
        val imageUrl = suspendCoroutine { continuation ->

            val imageRef = storageReference
                .child(STORAGE_CLIENT_IMAGE)
                .child("${UUID.randomUUID()}")

            val uploadTask = imageRef.putFile(uriImg)
            uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                imageRef.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    continuation.resume(downloadUri)
                } else {
                }
            }
        }

        return imageUrl.toString()
    }
}
