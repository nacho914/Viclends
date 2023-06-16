package victor.paez.usecases.dashboard

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import victor.paez.client.repository.ClientRepository
import victor.paez.usecases.dashboard.model.ClientListUI
import javax.inject.Inject

class GetClientListUseCase @Inject constructor(
    private val clientRepository: ClientRepository,
) {
    suspend operator fun invoke(): Flow<List<ClientListUI>> {
        return clientRepository.getClientList().map { clientList ->
            clientList.map { ClientListUI.fromClientDtoToClientListUI(it) }
        }
    }
}
