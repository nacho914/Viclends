package victor.paez.account.datasource

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import victor.paez.account.model.AccountDTO
import victor.paez.account.model.AddAccountDTO
import victor.paez.util.ACCOUNT_PATH
import victor.paez.util.DEBT_FIREBASE
import victor.paez.util.ID_CLIENT_FIREBASE
import victor.paez.util.MAIN_PATH
import victor.paez.util.MASTER_ID
import victor.paez.util.REVENUE_FIREBASE
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AccountDataSourceImp @Inject constructor(
    private val firestore: FirebaseFirestore,
) : AccountDataSource {

    override fun getAccountsList(clientId: String): Flow<List<AccountDTO>> = flow {
        val clientList = suspendCoroutine { finalAccount -> // mutableListOf<ClientDTO>()
            firestore
                .collection(MAIN_PATH)
                .document(MASTER_ID)
                .collection(ACCOUNT_PATH)
                .whereEqualTo(ID_CLIENT_FIREBASE, clientId)
                .get()
                .addOnSuccessListener { data ->
                    val clientList = mutableListOf<AccountDTO>()
                    for (account in data) {
                        clientList.add(AccountDTO.getAccountDTO(account))
                    }
                    finalAccount.resume(clientList)
                }.addOnFailureListener {
                    Log.d("Nacho", "Something fail")
                }
        }
        emit(clientList)
    }

    override fun addAccount(account: AddAccountDTO): Flow<Boolean> =
        flow {
            val bResponse = suspendCoroutine { response ->

                firestore.runTransaction { transaction ->
                    val mainDocRef = firestore.collection(MAIN_PATH)

                    // Update debt and revenue from resume information
                    val docResume = mainDocRef.document(MASTER_ID)
                    val snapshot = transaction.get(docResume)
                    val updatedDebt = snapshot.getLong(DEBT_FIREBASE)!! + account.debt
                    val updatedRevenue = snapshot.getLong(REVENUE_FIREBASE)!! + account.revenue
                    transaction.update(docResume, DEBT_FIREBASE, updatedDebt)
                    transaction.update(docResume, REVENUE_FIREBASE, updatedRevenue)

                    // Add client to collection Firebase
                    val docRef = mainDocRef
                        .document(MASTER_ID)
                        .collection(ACCOUNT_PATH)
                        .document()

                    transaction.set(docRef, account)

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
