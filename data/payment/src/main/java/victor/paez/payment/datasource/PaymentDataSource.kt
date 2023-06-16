package victor.paez.payment.datasource

import kotlinx.coroutines.flow.Flow
import victor.paez.payment.model.PaymentDTO

interface PaymentDataSource {
    fun getPaymentList(accountId: String): Flow<List<PaymentDTO>>
}
