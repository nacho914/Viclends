package victor.paez.resumeinformation.model

import com.google.firebase.firestore.DocumentSnapshot
import victor.paez.util.DEBT
import victor.paez.util.PEOPLE
import victor.paez.util.REVENUE
import victor.paez.util.TOTAL_EVERY_WEEK

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
                totalLends = document.getLong(DEBT)?.toInt() ?: 0,
                totalEveryWeek = document.getLong(TOTAL_EVERY_WEEK)?.toInt() ?: 0,
                people = document.getLong(PEOPLE)?.toInt() ?: 0,
                revenue = document.getLong(REVENUE)?.toInt() ?: 0,
            )
    }
}
