package victor.paez.account.repository

import kotlinx.coroutines.flow.Flow
import victor.paez.account.model.AccountDTO
import victor.paez.account.model.AddAccountDTO

interface AccountRepository {
    suspend fun getAccountList(clientId: String): Flow<List<AccountDTO>>
    suspend fun addAccount(addAccountDTO: AddAccountDTO): Flow<Boolean>
    suspend fun getAccountInformation(accountId: String): Flow<AccountDTO>
    suspend fun deleteAccount(accountId: String): Flow<Boolean>
}
