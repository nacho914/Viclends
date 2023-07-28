package victor.paez.client.model

import com.google.firebase.firestore.DocumentSnapshot
import victor.paez.util.BIRTHDAY_FIREBASE
import victor.paez.util.DEBT_FIREBASE
import victor.paez.util.DELAY_FIREBASE
import victor.paez.util.IMAGE_URL
import victor.paez.util.NAME_CLIENT_FIREBASE
import victor.paez.util.ORIGINAL_DEBT_FIREBASE
import victor.paez.util.ORIGINAL_DELAY_FIREBASE
import victor.paez.util.ORIGINAL_REVENUE_FIREBASE
import victor.paez.util.PHONE_FIREBASE
import victor.paez.util.REVENUE_FIREBASE
import victor.paez.util.START_DATE
import java.util.Date

data class ClientDTO(
    val id: String? = "",
    val name: String? = "",
    val phone: String? = "",
    val debt: Int? = 0,
    val revenue: Int? = 0,
    val delay: Int? = 0,
    val originalDebt: Int? = 0,
    val originalRevenue: Int? = 0,
    val originalDelay: Int? = 0,
    val birthday: Date? = null,
    val startDate: Date? = null,
    val imageUrl: String = "",
) {
    companion object {
        fun getClientDTO(
            document: DocumentSnapshot,
        ): ClientDTO =
            ClientDTO(
                id = document.id,
                name = document.getString(NAME_CLIENT_FIREBASE).orEmpty(),
                phone = document.getString(PHONE_FIREBASE).orEmpty(),
                debt = document.getLong(DEBT_FIREBASE)?.toInt() ?: 0,
                revenue = document.getLong(REVENUE_FIREBASE)?.toInt() ?: 0,
                delay = document.getLong(DELAY_FIREBASE)?.toInt() ?: 0,
                birthday = document.getDate(BIRTHDAY_FIREBASE),
                startDate = document.getDate(START_DATE),
                imageUrl = document.getString(IMAGE_URL).orEmpty(),
                originalDebt = document.getLong(ORIGINAL_DEBT_FIREBASE)?.toInt() ?: 0,
                originalRevenue = document.getLong(ORIGINAL_REVENUE_FIREBASE)?.toInt() ?: 0,
                originalDelay = document.getLong(ORIGINAL_DELAY_FIREBASE)?.toInt() ?: 0,
            )
    }
}
