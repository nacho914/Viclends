package victor.paez.client.datasource

import kotlinx.coroutines.flow.Flow
import victor.paez.client.model.ClientDTO

interface ClientDatasource {
    fun getClientList(): Flow<List<ClientDTO>>
}
