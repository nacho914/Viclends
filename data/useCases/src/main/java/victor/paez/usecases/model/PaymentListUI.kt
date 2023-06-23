package victor.paez.usecases.model
import victor.paez.payment.model.PaymentDTO
import java.util.Date

data class PaymentListUI(
    val total: Int? = 0,
    val debt: Int? = 0,
    val revenue: Int? = 0,
    val date: Date? = null,
) {
    companion object {
        fun getPaymentDTO(
            paymentDTO: PaymentDTO,
        ): PaymentListUI =
            PaymentListUI(
                total = paymentDTO.total,
                debt = paymentDTO.debt,
                revenue = paymentDTO.revenue,
                date = paymentDTO.date,
            )
    }
}
