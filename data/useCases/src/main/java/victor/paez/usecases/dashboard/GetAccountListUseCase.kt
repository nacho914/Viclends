package victor.paez.usecases.dashboard

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import victor.paez.account.repository.AccountRepository
import victor.paez.usecases.dashboard.model.AccountListUI
import javax.inject.Inject

class GetAccountListUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    operator fun invoke(clientId: String): Flow<List<AccountListUI>> {
        return accountRepository.getAccountList(clientId).map { accountList ->
            accountList.map { AccountListUI.fromAccountDtoToAccountListUI(it) }
        }
    }
}
