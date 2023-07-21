package victor.paez.usecases.model

import kotlinx.coroutines.delay
import victor.paez.resumeinformation.model.ResumeInformation

data class DashboardData(
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
        fun fromResumeInformationToDashboardData(resumeInformation: ResumeInformation):
            DashboardData =
            DashboardData(
                debt = resumeInformation.debt,
                revenue = resumeInformation.revenue,
                delay = resumeInformation.delay,
                originalDebt = resumeInformation.originalDebt,
                originalRevenue = resumeInformation.originalRevenue,
                originalDelay = resumeInformation.originalDelay,
                totalEveryWeek = resumeInformation.totalEveryWeek,
                people = resumeInformation.people,
            )
    }
}
