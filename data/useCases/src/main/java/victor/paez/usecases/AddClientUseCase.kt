package victor.paez.usecases

import android.net.Uri
import kotlinx.coroutines.flow.Flow
import victor.paez.client.repository.ClientRepository
import victor.paez.image.repository.ImageRepository
import victor.paez.usecases.model.ClientAddUI
import javax.inject.Inject

class AddClientUseCase @Inject constructor(
    private val clientRepository: ClientRepository,
    private val imageRepository: ImageRepository,
) {
    suspend operator fun invoke(clientAddUI: ClientAddUI, urlImg: Uri): Flow<Boolean> {
        imageRepository.uploadImage(urlImg).let {
            return clientRepository.addClient(
                ClientAddUI.fromClientAddUIToClientAddDTO(
                    clientAddUI = clientAddUI,
                    imgUrl = it,
                ),
            )
        }
    }
}
