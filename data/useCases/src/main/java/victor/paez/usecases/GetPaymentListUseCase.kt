package victor.paez.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import victor.paez.payment.repository.PaymentRepository
import victor.paez.usecases.model.PaymentListUI
import javax.inject.Inject

class GetPaymentListUseCase @Inject constructor(
    private val paymentRepository: PaymentRepository,
) {
    suspend operator fun invoke(paymentId: String): Flow<List<PaymentListUI>> {
        return paymentRepository.getPaymentList(paymentId).map { paymentList ->
            paymentList.map { PaymentListUI.getPaymentDTO(it) }
        }
    }
}
