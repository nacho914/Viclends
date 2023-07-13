package victor.paez.addpayment.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import victor.paez.usecases.AddPaymentUseCase
import victor.paez.usecases.GetDebtInformationUseCase
import victor.paez.usecases.model.AccountUI
import victor.paez.usecases.model.PaymentAddUI
import javax.inject.Inject

@HiltViewModel
class AddPaymentViewModel @Inject constructor(
    private val getDebtInformationUseCase: GetDebtInformationUseCase,
    private val addPaymentUseCase: AddPaymentUseCase,
) : ViewModel() {
    var paymentAddUI = mutableStateOf(PaymentAddUI())
        private set

    var accountInformation = mutableStateOf(AccountUI())
        private set

    var paymentText = mutableStateOf("")
        private set

    var isLoading = mutableStateOf(false)
        private set

    var isPaymentAdd = mutableStateOf(false)
        private set

    fun getAccountInformation(accountId: String) {
        viewModelScope.launch {
            isLoading.value = true
            getDebtInformationUseCase.invoke(accountId).collect {
                isLoading.value = false
                accountInformation.value = it
            }
        }
    }

    fun addAccount() {
        isLoading.value = true
        viewModelScope.launch {
            paymentAddUI.value.apply {
                payment = paymentText.value.toInt()
            }
            addPaymentUseCase.invoke(paymentAddUI.value, accountInformation.value).collect {
                isPaymentAdd.value = it
            }
            isLoading.value = false
        }
    }

    fun onlyNumbers(payment: String): Boolean = payment.matches(Regex("^\\d+\$"))

    fun isEnable() = paymentText.value.isNotEmpty() &&
        paymentAddUI.value.date != 0L &&
        paymentText.value.toInt() <= getTotalDebt()

    fun getTotalDebt() = accountInformation.value.debt +
        accountInformation.value.revenue

    fun confirmPayment() {
        getAccountInformation(accountInformation.value.idAccount.toString())
        paymentText.value = ""
        isPaymentAdd.value = false
    }
}
