package victor.paez.account.repository

import kotlinx.coroutines.flow.Flow
import victor.paez.account.datasource.AccountDataSource
import victor.paez.account.model.AccountDTO
import javax.inject.Inject

class AccountRepositoryImp @Inject constructor(
    private val firestoreDataSource: AccountDataSource,
) : AccountRepository {
    override fun getAccountList(clientId: String): Flow<List<AccountDTO>> =
        firestoreDataSource.getAccountsList(clientId)
}
