package victor.paez.image.repository

import android.net.Uri
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import victor.paez.image.datasource.ImageDataSource
import javax.inject.Inject

class ImageRepositoryImp @Inject constructor(
    private val imageDataSource: ImageDataSource,
    private val dispatcher: CoroutineDispatcher,
) : ImageRepository {
    override suspend fun uploadImage(urlImg: Uri): String = withContext(dispatcher) {
        imageDataSource.uploadImage(urlImg)
    }
}
