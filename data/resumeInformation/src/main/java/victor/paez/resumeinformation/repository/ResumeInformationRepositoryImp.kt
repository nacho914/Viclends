package victor.paez.resumeinformation.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import victor.paez.resumeinformation.datasource.ResumeInformationDataSource
import victor.paez.resumeinformation.model.ResumeInformation
import javax.inject.Inject

class ResumeInformationRepositoryImp
@Inject constructor(
    private val firestoreDataSource: ResumeInformationDataSource,
    private val dispatcher: CoroutineDispatcher,
) : ResumeInformationRepository {
    override suspend fun getResumeInformation(): Flow<ResumeInformation> = withContext(dispatcher) {
        firestoreDataSource.getResumeInformation()
    }

    override suspend fun addClient(): Boolean = withContext(dispatcher) {
        firestoreDataSource.addClient()
    }
}
