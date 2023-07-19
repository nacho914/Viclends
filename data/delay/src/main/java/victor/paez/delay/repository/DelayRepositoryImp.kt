package victor.paez.delay.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import victor.paez.delay.datasource.DelayDataSource
import victor.paez.delay.model.AddDelayDTO
import javax.inject.Inject

class DelayRepositoryImp @Inject constructor(
    private val firestoreDataSource: DelayDataSource,
    private val dispatcher: CoroutineDispatcher,
) : DelayRepository {
    override suspend fun addPayment(addDelayDTO: AddDelayDTO): Flow<Boolean> = withContext(dispatcher) {
        firestoreDataSource.addDelay(addDelayDTO)
    }
}
