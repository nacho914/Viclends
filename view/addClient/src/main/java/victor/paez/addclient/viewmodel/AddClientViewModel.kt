package victor.paez.addclient.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
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
    var isLoading = mutableStateOf(false)
        private set

    var isWrongPhoneFormat = mutableStateOf(false)
        private set

    var isWrongNameFormat = mutableStateOf(false)
        private set

    var isClientAdd = mutableStateOf(false)
        private set

    fun isEnable() = nameClient.value != "" &&
        phoneClient.value != "" &&
        client.value.birthday != 0L &&
        client.value.startDate != 0L &&
        !isWrongPhoneFormat.value &&
        !isWrongNameFormat.value

    fun checkPhoneFormat(phone: String) {
        isWrongPhoneFormat.value = !phone.matches(Regex("^\\d{2}-\\d{4}-\\d{2}-\\d{2}-\\d{2}$"))
    }

    fun checkNameFormat(name: String) {
        isWrongNameFormat.value = !name.matches(Regex("^(?:\\p{Lu}[\\p{L} ]*)?\$"))
    }

    fun addClient() {
        isLoading.value = true
        viewModelScope.launch {
            client.value.name = nameClient.value
            client.value.phone = phoneClient.value
            addClientUseCase(client.value).collect {
                isClientAdd.value = it
            }
            isClientAdd.value = true
            isLoading.value = false
        }
    }

    fun addConfirm() {
        isClientAdd.value = false
        phoneClient.value = ""
        nameClient.value = ""
    }
}
