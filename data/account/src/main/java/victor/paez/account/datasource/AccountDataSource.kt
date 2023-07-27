package victor.paez.account.datasource

import kotlinx.coroutines.flow.Flow
import victor.paez.account.model.AccountDTO
import victor.paez.account.model.AddAccountDTO

interface AccountDataSource {
    fun getAccountsList(clientId: String): Flow<List<AccountDTO>>
    fun addAccount(account: AddAccountDTO): Flow<Boolean>
    fun getAccountInformation(accountId: String): Flow<AccountDTO>
    fun deleteAccount(accountId: String): Flow<Boolean>
}
