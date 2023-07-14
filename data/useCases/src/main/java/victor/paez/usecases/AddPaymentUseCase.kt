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

        // TODO: Check the logic here, its wrong
        if (payment.payment == (account.debt + account.revenue + account.delay)) {
            paymentDTO.debtPayment = payment.payment - (account.revenue + account.delay)
            paymentDTO.revenuePayment = payment.payment - (account.debt + account.delay)
            paymentDTO.delayPayment = payment.payment - (account.debt + account.revenue)
        } // The payment is less than the debt so no payment to revenue
        else if (payment.payment <= account.debt) {
            paymentDTO.debtPayment = payment.payment
            paymentDTO.revenuePayment = 0
            paymentDTO.delayPayment = 0
        } // The payment is less than the revenue so no payment to delay
        else if (payment.payment <= account.revenue) {
            paymentDTO.debtPayment = account.debt
            paymentDTO.revenuePayment = payment.payment
            paymentDTO.delayPayment = 0
        } // In this case for sure there is a payment to the delay
        else {
            paymentDTO.debtPayment = account.debt
            paymentDTO.revenuePayment = account.revenue
            paymentDTO.delayPayment = payment.payment - account.revenue
        }

        return paymentRepository.addPayment(paymentDTO)
    }
}
