package victor.paez.addclient.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import victor.paez.usecases.AddClientUseCase
import victor.paez.usecases.model.ClientAddUI
import javax.inject.Inject

@HiltViewModel
class AddClientViewModel @Inject constructor(
    private val addClientUseCase: AddClientUseCase,
) : ViewModel() {

    var client = mutableStateOf(ClientAddUI())
        private set

    var nameClient = mutableStateOf("")
        private set
    var phoneClient = mutableStateOf("")
        private set
/*    var birthdayClient = mutableStateOf(0L)
        private set
    var dateClient = mutableStateOf(0L)
        private set*/

    var isLoading = mutableStateOf(false)
        private set

    fun isEnable() = nameClient.value != "" &&
        phoneClient.value != "" &&
        client.value.birthday != 0L &&
        client.value.startDate != 0L

    fun addClient() {
        isLoading.value = true
        viewModelScope.launch {
            client.value.name = nameClient.value
            client.value.phone = phoneClient.value

            addClientUseCase(client.value).collect() {
                if (it) {
                    println("okay")
                } else {
                    println("Fail")
                }
            }
            isLoading.value = false
        }
    }
}
