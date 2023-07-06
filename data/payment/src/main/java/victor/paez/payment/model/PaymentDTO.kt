package victor.paez.payment.model

import com.google.firebase.firestore.DocumentSnapshot
import victor.paez.util.DATE_FIREBASE
import victor.paez.util.DEBT_FIREBASE
import victor.paez.util.ID_ACCOUNT_FIREBASE
import victor.paez.util.ID_CLIENT_FIREBASE
import victor.paez.util.REVENUE_FIREBASE
import victor.paez.util.TOTAL_FIREBASE
import java.util.Date

data class PaymentDTO(
    val idClient: String? = "",
    val idAccount: String? = "",
    val total: Int? = 0,
    val debt: Int? = 0,
    val revenue: Int? = 0,
    val date: Date? = null,
) {
    companion object {
        fun getPaymentDTO(
            document: DocumentSnapshot,
        ): PaymentDTO =
            PaymentDTO(
                idClient = document.getString(ID_CLIENT_FIREBASE).orEmpty(),
                idAccount = document.getString(ID_ACCOUNT_FIREBASE).orEmpty(),
                total = document.getLong(TOTAL_FIREBASE)?.toInt() ?: 0,
                debt = document.getLong(DEBT_FIREBASE)?.toInt() ?: 0,
                revenue = document.getLong(REVENUE_FIREBASE)?.toInt() ?: 0,
                date = document.getDate(DATE_FIREBASE),
            )
    }
}
