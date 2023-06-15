package victor.paez.account.repository

import kotlinx.coroutines.flow.Flow
import victor.paez.account.model.AccountDTO

interface AccountRepository {
    fun getAccountList(clientId: String): Flow<List<AccountDTO>>
}
