package victor.paez.account.model

import com.google.firebase.firestore.DocumentSnapshot
import victor.paez.util.ACTIVE_FIREBASE
import victor.paez.util.DATE_FIREBASE
import victor.paez.util.DEBT_FIREBASE
import victor.paez.util.DELAY_FIREBASE
import victor.paez.util.ID_CLIENT_FIREBASE
import victor.paez.util.NAME_FIREBASE
import victor.paez.util.ORIGINAL_DEBT_FIREBASE
import victor.paez.util.ORIGINAL_DELAY_FIREBASE
import victor.paez.util.ORIGINAL_REVENUE_FIREBASE
import victor.paez.util.REVENUE_FIREBASE
import java.util.Date

data class AccountDTO(
    val id: String? = "",
    val idClient: String? = "",
    val active: Boolean? = false,
    val name: String? = "",
    val debt: Int? = 0,
    val revenue: Int? = 0,
    val originalDebt: Int? = 0,
    val originalRevenue: Int? = 0,
    val date: Date? = null,
    val delay: Int = 0,
    val originalDelay: Int = 0,
) {
    companion object {
        fun getAccountDTO(
            document: DocumentSnapshot,
        ): AccountDTO =
            AccountDTO(
                id = document.id,
                idClient = document.getString(ID_CLIENT_FIREBASE).orEmpty(),
                active = document.getBoolean(ACTIVE_FIREBASE),
                name = document.getString(NAME_FIREBASE).orEmpty(),
                debt = document.getLong(DEBT_FIREBASE)?.toInt() ?: 0,
                revenue = document.getLong(REVENUE_FIREBASE)?.toInt() ?: 0,
                originalDebt = document.getLong(ORIGINAL_DEBT_FIREBASE)?.toInt() ?: 0,
                originalRevenue = document.getLong(ORIGINAL_REVENUE_FIREBASE)?.toInt() ?: 0,
                date = document.getDate(DATE_FIREBASE),
                originalDelay = document.getLong(ORIGINAL_DELAY_FIREBASE)?.toInt() ?: 0,
                delay = document.getLong(DELAY_FIREBASE)?.toInt() ?: 0,
            )
    }
}
