package victor.paez.resumeinformation.model

import com.google.firebase.firestore.DocumentSnapshot
import victor.paez.resumeinformation.PEOPLE
import victor.paez.resumeinformation.REVENUE
import victor.paez.resumeinformation.TOTAL_EVERY_WEEK
import victor.paez.resumeinformation.TOTAL_LENDS

data class ResumeInformation(
    var totalLends: Int = 1,
    var people: Int = 1,
    var totalEveryWeek: Int = 1,
    var revenue: Int = 1,
) {
    companion object {

        fun getResumeInformation(
            document: DocumentSnapshot,
        ): ResumeInformation =
            ResumeInformation(
                totalLends = document.getLong(TOTAL_LENDS)?.toInt() ?: 0,
                totalEveryWeek = document.getLong(TOTAL_EVERY_WEEK)?.toInt() ?: 0,
                people = document.getLong(PEOPLE)?.toInt() ?: 0,
                revenue = document.getLong(REVENUE)?.toInt() ?: 0,
            )
    }
}
