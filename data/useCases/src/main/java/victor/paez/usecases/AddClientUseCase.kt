package victor.paez.usecases

import kotlinx.coroutines.flow.Flow
import victor.paez.client.repository.ClientRepository
import victor.paez.usecases.model.ClientAddUI
import javax.inject.Inject

class AddClientUseCase @Inject constructor(
    private val clientRepository: ClientRepository,
) {
    suspend operator fun invoke(clientAddUI: ClientAddUI): Flow<Boolean> =
        clientRepository.addClient(ClientAddUI.fromClientAddUIToClientAddDTO(clientAddUI))
}
