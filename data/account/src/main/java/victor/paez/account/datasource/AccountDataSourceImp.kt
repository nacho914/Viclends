package victor.paez.account.datasource

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import victor.paez.account.ACCOUNT_PATH
import victor.paez.account.ID_CLIENT_FIREBASE
import victor.paez.account.MAIN_PATH
import victor.paez.account.MASTER_ID
import victor.paez.account.model.AccountDTO
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
}
