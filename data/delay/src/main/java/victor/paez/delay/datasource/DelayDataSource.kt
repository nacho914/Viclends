package victor.paez.delay.datasource

import kotlinx.coroutines.flow.Flow
import victor.paez.delay.model.AddDelayDTO

interface DelayDataSource {
    fun addDelay(addDelayDTO: AddDelayDTO): Flow<Boolean>
}
