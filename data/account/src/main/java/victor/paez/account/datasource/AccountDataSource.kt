package victor.paez.account.datasource

import kotlinx.coroutines.flow.Flow
import victor.paez.account.model.AccountDTO

interface AccountDataSource {
    fun getAccountsList(clientId: String): Flow<List<AccountDTO>>
}
