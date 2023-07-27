package victor.paez.account.datasource

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import victor.paez.account.model.AccountDTO
import victor.paez.account.model.AddAccountDTO
import victor.paez.util.ACCOUNT_PATH
import victor.paez.util.CLIENT_PATH
import victor.paez.util.DEBT_FIREBASE
import victor.paez.util.DELAY_FIREBASE
import victor.paez.util.ID_CLIENT_FIREBASE
import victor.paez.util.MAIN_PATH
import victor.paez.util.MASTER_ID
import victor.paez.util.ORIGINAL_DEBT_FIREBASE
import victor.paez.util.ORIGINAL_DELAY_FIREBASE
import victor.paez.util.ORIGINAL_REVENUE_FIREBASE
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

                    // Get master and client
                    val docResume = mainDocRef.document(MASTER_ID)
                    val snapshot = transaction.get(docResume)

                    val docClient = mainDocRef.document(MASTER_ID).collection(CLIENT_PATH).document(account.idClient)
                    val clientSnapshot = transaction.get(docClient)

                    // Update debt and revenue from resume information
                    val updatedDebt = snapshot.getLong(DEBT_FIREBASE)!! + account.debt
                    val updatedRevenue = snapshot.getLong(REVENUE_FIREBASE)!! + account.revenue
                    val updatedOriginalDebt = snapshot.getLong(ORIGINAL_DEBT_FIREBASE)!! + account.debt
                    val updatedOriginalRevenue = snapshot.getLong(ORIGINAL_REVENUE_FIREBASE)!! + account.revenue
                    transaction.update(docResume, DEBT_FIREBASE, updatedDebt)
                    transaction.update(docResume, REVENUE_FIREBASE, updatedRevenue)
                    transaction.update(docResume, ORIGINAL_DEBT_FIREBASE, updatedOriginalDebt)
                    transaction.update(docResume, ORIGINAL_REVENUE_FIREBASE, updatedOriginalRevenue)

                    // Update debt and revenue from client information
                    val updatedClientDebt = clientSnapshot.getLong(DEBT_FIREBASE)!! + account.debt
                    val updatedClientRevenue = clientSnapshot.getLong(REVENUE_FIREBASE)!! + account.revenue
                    val updatedClientOriginalDebt = clientSnapshot.getLong(ORIGINAL_DEBT_FIREBASE)!! + account.debt
                    val updatedClientOriginalRevenue = clientSnapshot.getLong(ORIGINAL_REVENUE_FIREBASE)!! + account.revenue
                    transaction.update(docClient, DEBT_FIREBASE, updatedClientDebt)
                    transaction.update(docClient, REVENUE_FIREBASE, updatedClientRevenue)
                    transaction.update(docClient, ORIGINAL_DEBT_FIREBASE, updatedClientOriginalDebt)
                    transaction.update(docClient, ORIGINAL_REVENUE_FIREBASE, updatedClientOriginalRevenue)

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

    override fun getAccountInformation(accountId: String): Flow<AccountDTO> = flow {
        val account = suspendCoroutine { finalAccount ->
            firestore
                .collection(MAIN_PATH)
                .document(MASTER_ID)
                .collection(ACCOUNT_PATH)
                .document(accountId)
                .get()
                .addOnSuccessListener { data ->
                    finalAccount.resume(AccountDTO.getAccountDTO(data))
                }.addOnFailureListener {
                    Log.d("Nacho", "Something fail")
                }
        }
        emit(account)
    }

    override fun deleteAccount(accountId: String): Flow<Boolean> = flow {
        val bResponse = suspendCoroutine { response ->

            firestore.runTransaction { transaction ->
                val mainDocRef = firestore.collection(MAIN_PATH)

                // Get the values from account
                val docAccount = mainDocRef.document(MASTER_ID).collection(ACCOUNT_PATH).document(accountId)
                val accountSnapshot = transaction.get(docAccount)
                val idClient = accountSnapshot.getString(ID_CLIENT_FIREBASE).toString()

                // Get the values from the client
                val docClient = mainDocRef.document(MASTER_ID).collection(CLIENT_PATH).document(idClient)
                val clientSnapshot = transaction.get(docClient)

                val originalDebtClient = clientSnapshot.getLong(ORIGINAL_DEBT_FIREBASE)!! - accountSnapshot.getLong(ORIGINAL_DEBT_FIREBASE)!!
                val originalRevenueClient = clientSnapshot.getLong(ORIGINAL_REVENUE_FIREBASE)!! - accountSnapshot.getLong(ORIGINAL_REVENUE_FIREBASE)!!
                val originalDelayClient = clientSnapshot.getLong(ORIGINAL_DELAY_FIREBASE)!! - accountSnapshot.getLong(
                    ORIGINAL_DELAY_FIREBASE,
                )!!

                val debtClient = clientSnapshot.getLong(DEBT_FIREBASE)!! - accountSnapshot.getLong(DEBT_FIREBASE)!!
                val revenueClient = clientSnapshot.getLong(REVENUE_FIREBASE)!! - accountSnapshot.getLong(REVENUE_FIREBASE)!!
                val delayClient = clientSnapshot.getLong(DELAY_FIREBASE)!! - accountSnapshot.getLong(
                    DELAY_FIREBASE,
                )!!

                // Get the values from resume table
                val docResume = mainDocRef.document(MASTER_ID)
                val resumeSnapshot = transaction.get(docResume)

                val originalDebtResume = resumeSnapshot.getLong(ORIGINAL_DEBT_FIREBASE)!! - accountSnapshot.getLong(ORIGINAL_DEBT_FIREBASE)!!
                val originalRevenueResume = resumeSnapshot.getLong(ORIGINAL_REVENUE_FIREBASE)!! - accountSnapshot.getLong(ORIGINAL_REVENUE_FIREBASE)!!
                val originalDelayResume = resumeSnapshot.getLong(ORIGINAL_DELAY_FIREBASE)!! - accountSnapshot.getLong(
                    ORIGINAL_DELAY_FIREBASE,
                )!!

                val debtResume = resumeSnapshot.getLong(DEBT_FIREBASE)!! - accountSnapshot.getLong(DEBT_FIREBASE)!!
                val revenueResume = resumeSnapshot.getLong(REVENUE_FIREBASE)!! - accountSnapshot.getLong(REVENUE_FIREBASE)!!
                val delayResume = resumeSnapshot.getLong(DELAY_FIREBASE)!! - accountSnapshot.getLong(
                    DELAY_FIREBASE,
                )!!

                // rest account to the client
                transaction.update(docClient, ORIGINAL_DEBT_FIREBASE, originalDebtClient)
                transaction.update(docClient, ORIGINAL_REVENUE_FIREBASE, originalRevenueClient)
                transaction.update(docClient, ORIGINAL_DELAY_FIREBASE, originalDelayClient)
                transaction.update(docClient, DEBT_FIREBASE, debtClient)
                transaction.update(docClient, REVENUE_FIREBASE, revenueClient)
                transaction.update(docClient, DELAY_FIREBASE, delayClient)

                // rest account to the resume
                transaction.update(docResume, ORIGINAL_DEBT_FIREBASE, originalDebtResume)
                transaction.update(docResume, ORIGINAL_REVENUE_FIREBASE, originalRevenueResume)
                transaction.update(docResume, ORIGINAL_DELAY_FIREBASE, originalDelayResume)
                transaction.update(docResume, DEBT_FIREBASE, debtResume)
                transaction.update(docResume, REVENUE_FIREBASE, revenueResume)
                transaction.update(docResume, DELAY_FIREBASE, delayResume)

                // delete client to collection Firebase
                transaction.delete(docAccount)

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
