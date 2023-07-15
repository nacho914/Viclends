package victor.paez.usecases

import kotlinx.coroutines.flow.Flow
import victor.paez.payment.model.AddPaymentDTO
import victor.paez.payment.repository.PaymentRepository
import victor.paez.usecases.model.AccountUI
import victor.paez.usecases.model.PaymentAddUI
import victor.paez.util.getCalendarTime
import javax.inject.Inject

class AddPaymentUseCase @Inject constructor(
    private val paymentRepository: PaymentRepository,
) {
    suspend operator fun invoke(
        payment: PaymentAddUI,
        account: AccountUI,
    ): Flow<Boolean> {
        val paymentDTO = AddPaymentDTO()
        paymentDTO.totalPayment = payment.payment
        paymentDTO.idClient = account.idClient.toString()
        paymentDTO.date = getCalendarTime(payment.date)
        paymentDTO.idAccount = account.idAccount.toString()

        paymentDTO.debtPayment = minOf(payment.payment, account.debt)

        val remainingAmount = payment.payment - paymentDTO.debtPayment
        paymentDTO.revenuePayment = minOf(remainingAmount, account.revenue)

        val remainingAmountAfterInterest = remainingAmount - paymentDTO.revenuePayment
        paymentDTO.delayPayment = minOf(remainingAmountAfterInterest, account.delay)

        return paymentRepository.addPayment(paymentDTO)
    }
}
