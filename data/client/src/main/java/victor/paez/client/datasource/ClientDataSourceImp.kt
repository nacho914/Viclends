package victor.paez.client.datasource

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import victor.paez.client.CLIENT_PATH
import victor.paez.client.MAIN_PATH
import victor.paez.client.MASTER_ID
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
}
