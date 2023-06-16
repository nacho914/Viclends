package victor.paez.payment.repository

import kotlinx.coroutines.flow.Flow
import victor.paez.payment.datasource.PaymentDataSource
import victor.paez.payment.model.PaymentDTO
import javax.inject.Inject

class PaymentRepositoryImp @Inject constructor(
    private val firestoreDataSource: PaymentDataSource,
) : PaymentRepository {
    override fun getPaymentList(accountId: String): Flow<List<PaymentDTO>> =
        firestoreDataSource.getPaymentList(accountId)
}
