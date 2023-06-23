package victor.paez.clientdetail.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import victor.paez.usecases.GetClientUseCase
import victor.paez.usecases.model.ClientUI
import javax.inject.Inject

@HiltViewModel
class ClientDetailViewModel @Inject constructor(
    private val getClientUseCase: GetClientUseCase,
) : ViewModel() {

    private var currentJob: Job? = null

    var clientDetail = mutableStateOf(ClientUI())
        private set

    var isLoading = mutableStateOf(true)
        private set

    fun getClientDetail(clientId: String) {
        currentJob?.cancel()
        currentJob = viewModelScope.launch {
            isLoading.value = true
            getClientUseCase.invoke(clientId).collect {
                isLoading.value = false
                clientDetail.value = it
            }
        }
    }
}
