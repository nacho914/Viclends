package victor.paez.client.repository

import kotlinx.coroutines.flow.Flow
import victor.paez.client.datasource.ClientDatasource
import victor.paez.client.model.ClientDTO
import javax.inject.Inject

class ClientRepositoryImp
@Inject constructor(
    private val firestoreDataSource: ClientDatasource,
) : ClientRepository {
    override fun getClientList(): Flow<List<ClientDTO>> = firestoreDataSource.getClientList()
    override fun getClient(clientId: String): Flow<ClientDTO> = firestoreDataSource.getClient(clientId)
}
