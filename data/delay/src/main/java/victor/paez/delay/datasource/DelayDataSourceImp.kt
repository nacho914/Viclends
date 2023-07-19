package victor.paez.delay.datasource

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import victor.paez.delay.model.AddDelayDTO
import victor.paez.util.ACCOUNT_PATH
import victor.paez.util.CLIENT_PATH
import victor.paez.util.DELAY_FIREBASE
import victor.paez.util.DELAY_PATH
import victor.paez.util.MAIN_PATH
import victor.paez.util.MASTER_ID
import victor.paez.util.ORIGINAL_DELAY_FIREBASE
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class DelayDataSourceImp @Inject constructor(
    private val firestore: FirebaseFirestore,
) : DelayDataSource {
    override fun addDelay(addDelayDTO: AddDelayDTO): Flow<Boolean> =
        flow {
            val bResponse = suspendCoroutine { response ->

                firestore.runTransaction { transaction ->
                    val mainDocRef = firestore.collection(MAIN_PATH)

                    // Get resume information, clients and accounts information
                    val docResume = mainDocRef.document(MASTER_ID)
                    val snapshot = transaction.get(docResume)

                    val docClient = mainDocRef.document(MASTER_ID).collection(CLIENT_PATH).document(addDelayDTO.idClient)
                    val clientSnapshot = transaction.get(docClient)

                    val docAccount = mainDocRef.document(MASTER_ID).collection(ACCOUNT_PATH).document(addDelayDTO.idAccount)
                    val accountSnapshot = transaction.get(docAccount)

                    // Update delay from resume information
                    val updatedDelay = snapshot.getLong(DELAY_FIREBASE)!! + addDelayDTO.delay
                    val updatedOriginalDelay = snapshot.getLong(ORIGINAL_DELAY_FIREBASE)!! + addDelayDTO.delay
                    transaction.update(docResume, DELAY_FIREBASE, updatedDelay)
                    transaction.update(docResume, ORIGINAL_DELAY_FIREBASE, updatedOriginalDelay)

                    // Update delay from client information
                    val updatedClientDelay = clientSnapshot.getLong(DELAY_FIREBASE)!! + addDelayDTO.delay
                    val updatedClientOriginalDelay = clientSnapshot.getLong(ORIGINAL_DELAY_FIREBASE)!! + addDelayDTO.delay
                    transaction.update(docClient, DELAY_FIREBASE, updatedClientDelay)
                    transaction.update(docClient, ORIGINAL_DELAY_FIREBASE, updatedClientOriginalDelay)

                    // Update delay from account information
                    val updatedAccountDelay = accountSnapshot.getLong(DELAY_FIREBASE)!! + addDelayDTO.delay
                    val updatedAccountOriginalDelay = accountSnapshot.getLong(ORIGINAL_DELAY_FIREBASE)!! + addDelayDTO.delay
                    transaction.update(docAccount, DELAY_FIREBASE, updatedAccountDelay)
                    transaction.update(docAccount, ORIGINAL_DELAY_FIREBASE, updatedAccountOriginalDelay)

                    // Add payment to collection Firebase
                    val docRef = mainDocRef
                        .document(MASTER_ID)
                        .collection(DELAY_PATH)
                        .document()

                    transaction.set(docRef, addDelayDTO)

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
