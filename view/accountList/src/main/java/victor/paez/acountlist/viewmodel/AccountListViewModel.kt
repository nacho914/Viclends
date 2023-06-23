package victor.paez.acountlist.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import victor.paez.usecases.GetAccountListUseCase
import victor.paez.usecases.model.AccountListUI
import javax.inject.Inject

@HiltViewModel
class AccountListViewModel @Inject constructor(
    private val getAccountListUseCase: GetAccountListUseCase,
) : ViewModel() {
    var accountList = mutableStateOf(listOf<AccountListUI>())
        private set

    var isLoading = mutableStateOf(true)
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
}
