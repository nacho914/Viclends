package victor.paez.addaccount.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import victor.paez.usecases.AddAccountUseCase
import victor.paez.usecases.model.AccountAddUI
import javax.inject.Inject

@HiltViewModel
class AddAccountViewModel @Inject constructor(
    private val addAccountUseCase: AddAccountUseCase,
) : ViewModel() {

    var account = mutableStateOf(AccountAddUI())
        private set
    var nameAccount = mutableStateOf("")
        private set
    var debtAccount = mutableStateOf("")
        private set
    var revenueAccount = mutableStateOf("")
        private set

    var isLoading = mutableStateOf(false)
        private set

    var isAccountAdd = mutableStateOf(false)
        private set

    fun isEnable() = nameAccount.value != "" &&
        debtAccount.value != "" &&
        revenueAccount.value != "" &&
        account.value.date != 0L

    fun addAccount() {
        isLoading.value = true
        viewModelScope.launch {
            account.value.apply {
                name = nameAccount.value
                debt = debtAccount.value.toInt()
                revenue = revenueAccount.value.toInt()
            }

            addAccountUseCase(account.value).collect {
                isAccountAdd.value = it
            }
            isLoading.value = false
        }
    }

    fun addConfirm() {
        isAccountAdd.value = false
        nameAccount.value = ""
        debtAccount.value = ""
        revenueAccount.value = ""
    }
}
