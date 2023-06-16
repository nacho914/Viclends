package victor.paez.payment.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import victor.paez.payment.datasource.PaymentDataSource
import victor.paez.payment.model.PaymentDTO
import javax.inject.Inject

class PaymentRepositoryImp @Inject constructor(
    private val firestoreDataSource: PaymentDataSource,
    private val dispatcher: CoroutineDispatcher,
) : PaymentRepository {
    override suspend fun getPaymentList(accountId: String): Flow<List<PaymentDTO>> = withContext(dispatcher) {
        firestoreDataSource.getPaymentList(accountId)
    }
}
