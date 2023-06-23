package victor.paez.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import victor.paez.account.repository.AccountRepository
import victor.paez.usecases.model.AccountListUI
import javax.inject.Inject

class GetAccountListUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(clientId: String): Flow<List<AccountListUI>> {
        return accountRepository.getAccountList(clientId).map { accountList ->
            accountList.map { AccountListUI.fromAccountDtoToAccountListUI(it) }
        }
    }
}
