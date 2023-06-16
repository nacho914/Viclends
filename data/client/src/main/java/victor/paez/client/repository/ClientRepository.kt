package victor.paez.client.repository

import kotlinx.coroutines.flow.Flow
import victor.paez.client.model.ClientDTO

interface ClientRepository {
    suspend fun getClientList(): Flow<List<ClientDTO>>
    suspend fun getClient(clientId: String): Flow<ClientDTO>
}
