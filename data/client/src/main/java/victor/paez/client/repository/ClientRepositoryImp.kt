package victor.paez.client.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import victor.paez.client.datasource.ClientDatasource
import victor.paez.client.model.ClientDTO
import javax.inject.Inject

class ClientRepositoryImp
@Inject constructor(
    private val firestoreDataSource: ClientDatasource,
    private val dispatcher: CoroutineDispatcher,
) : ClientRepository {
    override suspend fun getClientList(): Flow<List<ClientDTO>> = withContext(dispatcher) {
        firestoreDataSource.getClientList()
    }

    override suspend fun getClient(clientId: String): Flow<ClientDTO> = withContext(dispatcher) {
        firestoreDataSource.getClient(clientId)
    }
}
