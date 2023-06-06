package victor.paez.resumeinformation.repository

import kotlinx.coroutines.flow.Flow
import victor.paez.resumeinformation.datasource.ResumeInformationDataSource
import victor.paez.resumeinformation.model.ResumeInformation
import javax.inject.Inject

class ResumeInformationRepositoryImp
@Inject constructor(
    private val firestoreDataSource: ResumeInformationDataSource,
) : ResumeInformationRepository {
    override fun getResumeInformation(): Flow<ResumeInformation> =
        firestoreDataSource.getResumeInformation()
}
