package victor.paez.usecases

import kotlinx.coroutines.flow.Flow
import victor.paez.client.repository.ClientRepository
import javax.inject.Inject

class DeleteClientUseCase @Inject constructor(
    private val clientRepository: ClientRepository,
) {
    suspend operator fun invoke(clientId: String): Flow<Boolean> {
        return clientRepository.deleteClient(clientId)
    }
}
