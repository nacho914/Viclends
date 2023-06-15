package victor.paez.client.repository

import kotlinx.coroutines.flow.Flow
import victor.paez.client.model.ClientDTO

interface ClientRepository {
    fun getClientList(): Flow<List<ClientDTO>>
    fun getClient(clientId: String): Flow<ClientDTO>
}
