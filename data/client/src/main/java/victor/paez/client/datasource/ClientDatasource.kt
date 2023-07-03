package victor.paez.client.datasource

import kotlinx.coroutines.flow.Flow
import victor.paez.client.model.ClientAddDTO
import victor.paez.client.model.ClientDTO

interface ClientDatasource {
    fun getClientList(): Flow<List<ClientDTO>>
    fun getClient(clientId: String): Flow<ClientDTO>
    fun addClient(client: ClientAddDTO): Flow<Boolean>
    fun deleteClient(clientId: String): Flow<Boolean>
}
