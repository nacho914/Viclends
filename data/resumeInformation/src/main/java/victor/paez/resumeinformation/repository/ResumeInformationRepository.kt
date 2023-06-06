package victor.paez.resumeinformation.repository

import kotlinx.coroutines.flow.Flow
import victor.paez.resumeinformation.model.ResumeInformation

interface ResumeInformationRepository {
    fun getResumeInformation(): Flow<ResumeInformation>
}
