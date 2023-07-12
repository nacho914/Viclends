package victor.paez.payment.datasource

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import victor.paez.payment.model.AddPaymentDTO
import victor.paez.payment.model.PaymentDTO
import victor.paez.util.ACCOUNT_PATH
import victor.paez.util.CLIENT_PATH
import victor.paez.util.DEBT_FIREBASE
import victor.paez.util.ID_ACCOUNT_FIREBASE
import victor.paez.util.MAIN_PATH
import victor.paez.util.MASTER_ID
import victor.paez.util.PAYMENT_PATH
import victor.paez.util.REVENUE_FIREBASE
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

    override fun addPayment(addPaymentDTO: AddPaymentDTO): Flow<Boolean> =
        flow {
            val bResponse = suspendCoroutine { response ->

                firestore.runTransaction { transaction ->
                    val mainDocRef = firestore.collection(MAIN_PATH)

                    // Get resume information, clients and accounts information
                    val docResume = mainDocRef.document(MASTER_ID)
                    val snapshot = transaction.get(docResume)

                    val docClient = mainDocRef.document(MASTER_ID).collection(CLIENT_PATH).document(addPaymentDTO.idClient)
                    val clientSnapshot = transaction.get(docClient)

                    val docAccount = mainDocRef.document(MASTER_ID).collection(ACCOUNT_PATH).document(addPaymentDTO.idAccount)
                    val accountSnapshot = transaction.get(docAccount)

                    // Update debt and revenue from resume information
                    val updatedDebt = snapshot.getLong(DEBT_FIREBASE)!! - addPaymentDTO.debtPayment
                    val updatedRevenue = snapshot.getLong(REVENUE_FIREBASE)!! - addPaymentDTO.revenuePayment
                    transaction.update(docResume, DEBT_FIREBASE, updatedDebt)
                    transaction.update(docResume, REVENUE_FIREBASE, updatedRevenue)

                    // Update debt and revenue from client information
                    val updatedClientDebt = clientSnapshot.getLong(DEBT_FIREBASE)!! - addPaymentDTO.debtPayment
                    val updatedClientRevenue = clientSnapshot.getLong(REVENUE_FIREBASE)!! - addPaymentDTO.revenuePayment
                    transaction.update(docClient, DEBT_FIREBASE, updatedClientDebt)
                    transaction.update(docClient, REVENUE_FIREBASE, updatedClientRevenue)

                    // Update debt and revenue from client information
                    val updatedAccountDebt = accountSnapshot.getLong(DEBT_FIREBASE)!! - addPaymentDTO.debtPayment
                    val updatedAccountRevenue = accountSnapshot.getLong(REVENUE_FIREBASE)!! - addPaymentDTO.revenuePayment
                    transaction.update(docAccount, DEBT_FIREBASE, updatedAccountDebt)
                    transaction.update(docAccount, REVENUE_FIREBASE, updatedAccountRevenue)

                    // Add payment to collection Firebase
                    val docRef = mainDocRef
                        .document(MASTER_ID)
                        .collection(PAYMENT_PATH)
                        .document()

                    transaction.set(docRef, addPaymentDTO)

                    null
                }.addOnSuccessListener {
                    response.resume(true)
                }.addOnFailureListener {
                    response.resume(false)
                    Log.d("Nacho", "Error Message :${it.message}")
                }
            }

            emit(bResponse)
        }
}
