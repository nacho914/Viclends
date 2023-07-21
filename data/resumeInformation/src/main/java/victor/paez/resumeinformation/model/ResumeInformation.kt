package victor.paez.resumeinformation.model

import com.google.firebase.firestore.DocumentSnapshot
import victor.paez.util.DEBT_FIREBASE
import victor.paez.util.DELAY_FIREBASE
import victor.paez.util.ORIGINAL_DEBT_FIREBASE
import victor.paez.util.ORIGINAL_DELAY_FIREBASE
import victor.paez.util.ORIGINAL_REVENUE_FIREBASE
import victor.paez.util.PEOPLE
import victor.paez.util.REVENUE_FIREBASE
import victor.paez.util.TOTAL_EVERY_WEEK

data class ResumeInformation(
    var debt: Int = 0,
    var revenue: Int = 0,
    var delay: Int = 0,
    var originalDebt: Int = 0,
    var originalRevenue: Int = 0,
    var originalDelay: Int = 0,
    var people: Int = 0,
    var totalEveryWeek: Int = 0,
) {
    companion object {
        fun getResumeInformation(
            document: DocumentSnapshot,
        ): ResumeInformation =
            ResumeInformation(
                debt = document.getLong(DEBT_FIREBASE)?.toInt() ?: 0,
                revenue = document.getLong(REVENUE_FIREBASE)?.toInt() ?: 0,
                delay = document.getLong(DELAY_FIREBASE)?.toInt() ?: 0,
                originalDebt = document.getLong(ORIGINAL_DEBT_FIREBASE)?.toInt() ?: 0,
                originalRevenue = document.getLong(ORIGINAL_REVENUE_FIREBASE)?.toInt() ?: 0,
                originalDelay = document.getLong(ORIGINAL_DELAY_FIREBASE)?.toInt() ?: 0,
                totalEveryWeek = document.getLong(TOTAL_EVERY_WEEK)?.toInt() ?: 0,
                people = document.getLong(PEOPLE)?.toInt() ?: 0,

            )
    }
}
