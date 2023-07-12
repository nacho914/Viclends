package victor.paez.payment.model
import java.util.Date

data class AddPaymentDTO(
    var idClient: String = "",
    var idAccount: String = "",
    var totalPayment: Int = 0,
    var debtPayment: Int = 0,
    var revenuePayment: Int = 0,
    var date: Date? = null,
)
