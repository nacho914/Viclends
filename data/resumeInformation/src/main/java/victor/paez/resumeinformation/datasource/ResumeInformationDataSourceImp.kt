package victor.paez.resumeinformation.datasource

import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import victor.paez.resumeinformation.model.ResumeInformation
import victor.paez.util.MAIN_PATH
import victor.paez.util.MASTER_ID
import victor.paez.util.PEOPLE
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ResumeInformationDataSourceImp @Inject constructor(
    private val firestore: FirebaseFirestore,
) : ResumeInformationDataSource {
    override fun getResumeInformation(): Flow<ResumeInformation> = flow {
        var resumeInformation = ResumeInformation()
        firestore.collection(MAIN_PATH).document(MASTER_ID)
            .get()
            .addOnSuccessListener { information ->
                resumeInformation = ResumeInformation.getResumeInformation(information)
            }.addOnFailureListener {
                Log.d("Nacho", "Something fail")
            }.await()
        emit(resumeInformation)
    }

    override suspend fun addClient(): Boolean =
        suspendCoroutine { result ->
            firestore
                .collection(MAIN_PATH)
                .document(MASTER_ID)
                .update(PEOPLE, FieldValue.increment(1))
                .addOnSuccessListener {
                    result.resume(true)
                }
                .addOnFailureListener {
                        e ->
                    Log.w("Nacho", "Error updating document", e)
                    result.resume(false)
                }
        }
}
