package victor.paez.acountlist.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import victor.paez.usecases.DeleteAccountUseCase
import victor.paez.usecases.GetAccountListUseCase
import victor.paez.usecases.model.AccountListUI
import javax.inject.Inject

@HiltViewModel
class AccountListViewModel @Inject constructor(
    private val getAccountListUseCase: GetAccountListUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase,
) : ViewModel() {
    var accountList = mutableStateOf(listOf<AccountListUI>())
        private set

    var isLoading = mutableStateOf(true)
        private set

    var isDeleting = mutableStateOf(false)
        private set

    var isAccountDeleted = mutableStateOf(false)
        private set

    fun getAccountList(clientId: String) {
        isLoading.value = true
        viewModelScope.launch {
            getAccountListUseCase.invoke(clientId).collect {
                isLoading.value = false
                accountList.value = it
            }
        }
    }

    fun deleteAccount(accountId: String) {
        isDeleting.value = true
        viewModelScope.launch {
            deleteAccountUseCase.invoke(accountId).collect {
                isAccountDeleted.value = it
            }
            isDeleting.value = false
        }
    }
}
