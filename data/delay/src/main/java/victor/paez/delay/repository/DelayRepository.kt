package victor.paez.delay.repository

import kotlinx.coroutines.flow.Flow
import victor.paez.delay.model.AddDelayDTO

interface DelayRepository {
    suspend fun addPayment(addDelayDTO: AddDelayDTO): Flow<Boolean>
}
