package victor.paez.account.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import victor.paez.account.datasource.AccountDataSource
import victor.paez.account.model.AccountDTO
import victor.paez.account.model.AddAccountDTO
import javax.inject.Inject

class AccountRepositoryImp @Inject constructor(
    private val firestoreDataSource: AccountDataSource,
    private val dispatcher: CoroutineDispatcher,
) : AccountRepository {
    override suspend fun getAccountList(clientId: String): Flow<List<AccountDTO>> = withContext(dispatcher) {
        firestoreDataSource.getAccountsList(clientId)
    }

    override suspend fun addAccount(addAccountDTO: AddAccountDTO): Flow<Boolean> = withContext(dispatcher) {
        firestoreDataSource.addAccount(addAccountDTO)
    }
}
