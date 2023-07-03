package victor.paez.client.datasource

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import victor.paez.client.CLIENT_PATH
import victor.paez.client.MAIN_PATH
import victor.paez.client.MASTER_ID
import victor.paez.client.PEOPLE
import victor.paez.client.RESUME_INFORMATION_PATH
import victor.paez.client.model.ClientAddDTO
import victor.paez.client.model.ClientDTO
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
                val docResume = mainDocRef.document(RESUME_INFORMATION_PATH)
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

                // Add client to the resume
                val docResume = mainDocRef.document(RESUME_INFORMATION_PATH)
                val snapshot = transaction.get(docResume)
                val deleteClient = snapshot.getLong(PEOPLE)!! - 1
                transaction.update(docResume, PEOPLE, deleteClient)

                // Add client to collection Firebase
                val docRef = mainDocRef.document(MASTER_ID).collection(CLIENT_PATH).document(clientId)
                transaction.delete(docRef)

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
