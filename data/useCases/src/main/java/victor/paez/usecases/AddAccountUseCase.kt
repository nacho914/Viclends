package victor.paez.usecases

import kotlinx.coroutines.flow.Flow
import victor.paez.account.repository.AccountRepository
import victor.paez.usecases.model.AccountAddUI
import javax.inject.Inject

class AddAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(accountAddUI: AccountAddUI): Flow<Boolean> =
        accountRepository.addAccount(
            AccountAddUI.fromAccountAddUIToAccountAddDTO(accountAddUI),
        )
}
