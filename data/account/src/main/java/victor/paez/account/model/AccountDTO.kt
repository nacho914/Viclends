package victor.paez.account.model

import com.google.firebase.firestore.DocumentSnapshot
import victor.paez.account.ACTIVE_FIREBASE
import victor.paez.account.DATE_FIREBASE
import victor.paez.account.DEBT_FIREBASE
import victor.paez.account.ID_CLIENT_FIREBASE
import victor.paez.account.NAME_FIREBASE
import victor.paez.account.ORIGINAL_DEBT_FIREBASE
import victor.paez.account.ORIGINAL_REVENUE_FIREBASE
import victor.paez.account.REVENUE_FIREBASE
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
            )
    }
}
