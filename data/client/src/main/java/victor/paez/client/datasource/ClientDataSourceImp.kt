package victor.paez.client.datasource

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import victor.paez.client.model.ClientAddDTO
import victor.paez.client.model.ClientDTO
import victor.paez.util.CLIENT_PATH
import victor.paez.util.DEBT_FIREBASE
import victor.paez.util.DELAY_FIREBASE
import victor.paez.util.MAIN_PATH
import victor.paez.util.MASTER_ID
import victor.paez.util.ORIGINAL_DEBT_FIREBASE
import victor.paez.util.ORIGINAL_DELAY_FIREBASE
import victor.paez.util.ORIGINAL_REVENUE_FIREBASE
import victor.paez.util.PEOPLE
import victor.paez.util.REVENUE_FIREBASE
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ClientDataSourceImp @Inject constructor(
    private val firestore: FirebaseFirestore,
) : ClientDatasource {

    override fun getClientList(): Flow<List<ClientDTO>> = flow {
        val clientList = suspendCoroutine { continuation -> // mutableListOf<ClientDTO>()
            firestore
                .collection(MAIN_PATH)
                .document(MASTER_ID)
                .collection(CLIENT_PATH)
                .get()
                .addOnSuccessListener { data ->
                    val clientList = mutableListOf<ClientDTO>()
                    for (client in data) {
                        clientList.add(ClientDTO.getClientDTO(client))
                    }
                    continuation.resume(clientList)
                }.addOnFailureListener {
                    Log.d("Nacho", "Something fail")
                }
        }
        emit(clientList)
    }

    override fun getClient(clientId: String): Flow<ClientDTO> =
        flow {
            val client = suspendCoroutine { continuation ->
                firestore
                    .collection(MAIN_PATH)
                    .document(MASTER_ID)
                    .collection(CLIENT_PATH)
                    .document(clientId)
                    .get()
                    .addOnSuccessListener { data ->
                        val client = ClientDTO.getClientDTO(data)
                        continuation.resume(client)
                    }.addOnFailureListener {
                        Log.d("Nacho", "Something fail")
                    }
            }
            emit(client)
        }

    override fun addClient(client: ClientAddDTO): Flow<Boolean> = flow {
        val bResponse = suspendCoroutine { response ->

            firestore.runTransaction { transaction ->
                val mainDocRef = firestore.collection(MAIN_PATH)

                // Add client to the resume
                val docResume = mainDocRef.document(MASTER_ID)
                val snapshot = transaction.get(docResume)
                val newClientAdd = snapshot.getLong(PEOPLE)!! + 1
                transaction.update(docResume, PEOPLE, newClientAdd)

                // Add client to collection Firebase
                val docRef = mainDocRef.document(MASTER_ID).collection(CLIENT_PATH).document()
                transaction.set(docRef, client)

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

    override fun deleteClient(clientId: String): Flow<Boolean> = flow {
        val bResponse = suspendCoroutine { response ->

            firestore.runTransaction { transaction ->
                val mainDocRef = firestore.collection(MAIN_PATH)

                // Get the values from resume table
                val docResume = mainDocRef.document(MASTER_ID)
                val resumeSnapshot = transaction.get(docResume)
                val deleteClient = resumeSnapshot.getLong(PEOPLE)!! - 1

                // Get the values from the client
                val docClient = mainDocRef.document(MASTER_ID).collection(CLIENT_PATH).document(clientId)
                val clientSnapshot = transaction.get(docClient)
                val originalDebtResume = resumeSnapshot.getLong(ORIGINAL_DEBT_FIREBASE)!! - clientSnapshot.getLong(ORIGINAL_DEBT_FIREBASE)!!
                val originalRevenueResume = resumeSnapshot.getLong(ORIGINAL_REVENUE_FIREBASE)!! - clientSnapshot.getLong(ORIGINAL_REVENUE_FIREBASE)!!
                val originalDelayResume = resumeSnapshot.getLong(ORIGINAL_DELAY_FIREBASE)!! - clientSnapshot.getLong(ORIGINAL_DELAY_FIREBASE)!!

                val debtResume = resumeSnapshot.getLong(DEBT_FIREBASE)!! - clientSnapshot.getLong(DEBT_FIREBASE)!!
                val revenueResume = resumeSnapshot.getLong(REVENUE_FIREBASE)!! - clientSnapshot.getLong(REVENUE_FIREBASE)!!
                val delayResume = resumeSnapshot.getLong(DELAY_FIREBASE)!! - clientSnapshot.getLong(DELAY_FIREBASE)!!

                // rest client to the resume
                transaction.update(docResume, PEOPLE, deleteClient)
                transaction.update(docResume, ORIGINAL_DEBT_FIREBASE, originalDebtResume)
                transaction.update(docResume, ORIGINAL_REVENUE_FIREBASE, originalRevenueResume)
                transaction.update(docResume, ORIGINAL_DELAY_FIREBASE, originalDelayResume)
                transaction.update(docResume, DEBT_FIREBASE, debtResume)
                transaction.update(docResume, REVENUE_FIREBASE, revenueResume)
                transaction.update(docResume, DELAY_FIREBASE, delayResume)

                // delete client to collection Firebase
                transaction.delete(docClient)

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
