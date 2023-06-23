package victor.paez.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import victor.paez.resumeinformation.model.ResumeInformation
import victor.paez.resumeinformation.repository.ResumeInformationRepository
import victor.paez.usecases.model.DashboardData
import javax.inject.Inject

class GetResumeDataUseCase @Inject constructor(
    private val resumeInformationRepository: ResumeInformationRepository,
) {
    suspend operator fun invoke(): Flow<DashboardData> {
        return resumeInformationRepository.getResumeInformation().map { value: ResumeInformation ->
            DashboardData.fromResumeInformationToDashboardData(value)
        }
    }
}
