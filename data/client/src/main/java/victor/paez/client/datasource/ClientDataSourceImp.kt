package victor.paez.client.datasource

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import victor.paez.client.CLIENT_PATH
import victor.paez.client.MAIN_PATH
import victor.paez.client.MASTER_ID
import victor.paez.client.model.ClientDTO
import javax.inject.Inject

class ClientDataSourceImp @Inject constructor(
    private val firestore: FirebaseFirestore,
) : ClientDatasource {

    override fun getClientList(): Flow<List<ClientDTO>> = flow {
        val clientList = mutableListOf<ClientDTO>()
        firestore.collection(MAIN_PATH).document(MASTER_ID).collection(CLIENT_PATH)
            .get()
            .addOnSuccessListener { data ->
                for (client in data) {
                    clientList.add(ClientDTO.getClientDTO(client))
                }
            }.addOnFailureListener {
                Log.d("Nacho", "Something fail")
            }.await()
        emit(clientList)
    }
}
