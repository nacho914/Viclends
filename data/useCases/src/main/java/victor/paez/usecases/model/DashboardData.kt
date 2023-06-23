package victor.paez.usecases.model

import victor.paez.resumeinformation.model.ResumeInformation

data class DashboardData(
    var totalLends: Int = 1,
    var people: Int = 1,
    var totalEveryWeek: Int = 1,
    var revenue: Int = 1,
) {
    companion object {
        fun fromResumeInformationToDashboardData(resumeInformation: ResumeInformation):
            DashboardData =
            DashboardData(
                totalLends = resumeInformation.totalLends,
                totalEveryWeek = resumeInformation.totalEveryWeek,
                people = resumeInformation.people,
                revenue = resumeInformation.revenue,
            )
    }
}
