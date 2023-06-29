package victor.paez.usecases

import android.net.Uri
import kotlinx.coroutines.flow.Flow
import victor.paez.client.repository.ClientRepository
import victor.paez.image.repository.ImageRepository
import victor.paez.resumeinformation.repository.ResumeInformationRepository
import victor.paez.usecases.model.ClientAddUI
import javax.inject.Inject

class AddClientUseCase @Inject constructor(
    private val clientRepository: ClientRepository,
    private val imageRepository: ImageRepository,
    private val resumeInformationRepository: ResumeInformationRepository,
) {
    suspend operator fun invoke(clientAddUI: ClientAddUI, urlImg: Uri): Flow<Boolean> {
        val imageUrl = imageRepository.uploadImage(urlImg)

        resumeInformationRepository.addClient()

        return clientRepository.addClient(
            ClientAddUI.fromClientAddUIToClientAddDTO(
                clientAddUI = clientAddUI,
                imgUrl = imageUrl,
            ),
        )
    }
}
