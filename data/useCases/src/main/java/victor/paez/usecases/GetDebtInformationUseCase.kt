package victor.paez.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import victor.paez.account.repository.AccountRepository
import victor.paez.usecases.model.AccountUI
import javax.inject.Inject

class GetDebtInformationUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(accountId: String): Flow<AccountUI> {
        return accountRepository.getAccountInformation(accountId).map {
            AccountUI.fromAccountDtoToAccountUI(it)
        }
    }
}
