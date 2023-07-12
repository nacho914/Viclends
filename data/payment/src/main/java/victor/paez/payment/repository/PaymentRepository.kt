package victor.paez.payment.repository

import kotlinx.coroutines.flow.Flow
import victor.paez.payment.model.AddPaymentDTO
import victor.paez.payment.model.PaymentDTO

interface PaymentRepository {
    suspend fun getPaymentList(accountId: String): Flow<List<PaymentDTO>>
    suspend fun addPayment(addPaymentDTO: AddPaymentDTO): Flow<Boolean>
}
