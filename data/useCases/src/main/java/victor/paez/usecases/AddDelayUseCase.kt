package victor.paez.usecases

import kotlinx.coroutines.flow.Flow
import victor.paez.delay.repository.DelayRepository
import victor.paez.usecases.model.AccountUI
import victor.paez.usecases.model.DelayAddUI
import javax.inject.Inject

class AddDelayUseCase @Inject constructor(
    private val delayRepository: DelayRepository,
) {
    suspend operator fun invoke(
        delay: DelayAddUI,
        account: AccountUI,
    ): Flow<Boolean> =
        delayRepository.addPayment(
            DelayAddUI.fromDelayAddUIToAddDelayDTO(delay, account),
        )
}
