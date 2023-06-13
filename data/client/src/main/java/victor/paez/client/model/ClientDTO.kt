package victor.paez.client.model

import com.google.firebase.firestore.DocumentSnapshot
import victor.paez.client.BIRTHDAY_FIREBASE
import victor.paez.client.DEBT_FIREBASE
import victor.paez.client.NAME_FIREBASE
import victor.paez.client.PHONE_FIREBASE
import victor.paez.client.REVENUE_FIREBASE
import victor.paez.client.START_DATE
import java.util.Date

data class ClientDTO(
    val name: String = "",
    val phone: String = "",
    val debt: Int = 0,
    val revenue: Int = 0,
    val birthday: Date?,
    val startDate: Date?,
) {
    companion object {
        fun getClientDTO(
            document: DocumentSnapshot,
        ): ClientDTO =
            ClientDTO(
                name = document.getString(NAME_FIREBASE).orEmpty(),
                phone = document.getString(PHONE_FIREBASE).orEmpty(),
                debt = document.getLong(DEBT_FIREBASE)?.toInt() ?: 0,
                revenue = document.getLong(REVENUE_FIREBASE)?.toInt() ?: 0,
                birthday = document.getDate(BIRTHDAY_FIREBASE),
                startDate = document.getDate(START_DATE),
            )
    }
}
