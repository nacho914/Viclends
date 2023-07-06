package victor.paez.payment.datasource

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import victor.paez.payment.model.PaymentDTO
import victor.paez.util.ID_ACCOUNT_FIREBASE
import victor.paez.util.MAIN_PATH
import victor.paez.util.MASTER_ID
import victor.paez.util.PAYMENT_PATH
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class PaymentDataSourceImp @Inject constructor(
    private val firestore: FirebaseFirestore,
) : PaymentDataSource {
    override fun getPaymentList(accountId: String): Flow<List<PaymentDTO>> = flow {
        val paymentList = suspendCoroutine { finalAccount ->
            firestore
                .collection(MAIN_PATH)
                .document(MASTER_ID)
                .collection(PAYMENT_PATH)
                .whereEqualTo(ID_ACCOUNT_FIREBASE, accountId)
                .get()
                .addOnSuccessListener { data ->
                    val paymentList = mutableListOf<PaymentDTO>()
                    for (payment in data) {
                        paymentList.add(PaymentDTO.getPaymentDTO(payment))
                    }
                    finalAccount.resume(paymentList)
                }.addOnFailureListener {
                    Log.d("Nacho", "Something fail")
                }
        }
        emit(paymentList)
    }
}
