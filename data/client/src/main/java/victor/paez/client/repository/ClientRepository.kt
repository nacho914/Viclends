package victor.paez.client.repository

import kotlinx.coroutines.flow.Flow
import victor.paez.client.model.ClientAddDTO
import victor.paez.client.model.ClientDTO

interface ClientRepository {
    suspend fun getClientList(): Flow<List<ClientDTO>>
    suspend fun getClient(clientId: String): Flow<ClientDTO>
    suspend fun addClient(client: ClientAddDTO): Flow<Boolean>
}
