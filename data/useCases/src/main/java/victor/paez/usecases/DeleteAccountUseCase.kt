package victor.paez.usecases

import kotlinx.coroutines.flow.Flow
import victor.paez.account.repository.AccountRepository
import javax.inject.Inject

class DeleteAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(accountId: String): Flow<Boolean> {
        return accountRepository.deleteAccount(accountId)
    }
}
