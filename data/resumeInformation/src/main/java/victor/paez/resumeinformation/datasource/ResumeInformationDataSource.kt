package victor.paez.resumeinformation.datasource

import kotlinx.coroutines.flow.Flow
import victor.paez.resumeinformation.model.ResumeInformation

interface ResumeInformationDataSource {
    fun getResumeInformation(): Flow<ResumeInformation>
    suspend fun addClient(): Boolean
}
