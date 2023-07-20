package victor.paez.adddelay.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import victor.paez.usecases.AddDelayUseCase
import victor.paez.usecases.GetDebtInformationUseCase
import victor.paez.usecases.model.AccountUI
import victor.paez.usecases.model.DelayAddUI
import javax.inject.Inject

@HiltViewModel
class AddDelayViewModel @Inject constructor(
    private val getDebtInformationUseCase: GetDebtInformationUseCase,
    private val addDelayUseCase: AddDelayUseCase,
) : ViewModel() {

    private var accountInformation = mutableStateOf(AccountUI())
    var delay = mutableStateOf(DelayAddUI())

    var delayText = mutableStateOf("")
        private set

    var descriptionText = mutableStateOf("")
        private set

    var isLoading = mutableStateOf(false)
        private set

    var isDelayAdd = mutableStateOf(false)
        private set

    fun addDelay() {
        isLoading.value = true
        viewModelScope.launch {
            delay.value.apply {
                delay = delayText.value.toInt()
                description = descriptionText.value
            }
            addDelayUseCase.invoke(delay.value, accountInformation.value).collect {
                isDelayAdd.value = it
            }
            isLoading.value = false
        }
    }

    fun getAccountInformation(accountId: String) {
        viewModelScope.launch {
            isLoading.value = true
            getDebtInformationUseCase.invoke(accountId).collect {
                isLoading.value = false
                accountInformation.value = it
            }
        }
    }

    fun confirmDelay() {
        getAccountInformation(accountInformation.value.idAccount.toString())
        delayText.value = ""
        descriptionText.value = ""
        isDelayAdd.value = false
    }

    fun getTotalDebt() = accountInformation.value.debt +
        accountInformation.value.revenue + accountInformation.value.delay

    fun onlyNumbers(payment: String): Boolean = payment.matches(Regex("^\\d+\$"))

    fun isEnable() = delayText.value.isNotEmpty() &&
        delay.value.date != 0L &&
        descriptionText.value.isNotEmpty()
}
