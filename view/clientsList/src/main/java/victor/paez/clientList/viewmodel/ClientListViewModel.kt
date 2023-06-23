package victor.paez.clientList.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import victor.paez.usecases.GetClientListUseCase
import victor.paez.usecases.model.ClientListUI
import javax.inject.Inject

@HiltViewModel
class ClientListViewModel @Inject constructor(
    private val getClientListUseCase: GetClientListUseCase,
) : ViewModel() {

    var clientList = mutableStateOf(listOf<ClientListUI>())
        private set

    var isLoading = mutableStateOf(true)
        private set

    init {
        getClientList()
    }
    private fun getClientList() {
        isLoading.value = true
        viewModelScope.launch {
            getClientListUseCase.invoke().collect {
                isLoading.value = false
                clientList.value = it
            }
        }
    }
}
