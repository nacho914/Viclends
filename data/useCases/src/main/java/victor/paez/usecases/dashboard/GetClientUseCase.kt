package victor.paez.usecases.dashboard

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import victor.paez.client.repository.ClientRepository
import victor.paez.usecases.dashboard.model.ClientUI
import javax.inject.Inject

class GetClientUseCase @Inject constructor(
    private val clientRepository: ClientRepository,
) {
    suspend operator fun invoke(clientId: String): Flow<ClientUI> {
        return clientRepository.getClient(clientId).map { clientDTO ->
            ClientUI.fromClientDtoToClientUI(clientDTO)
        }
    }
}
