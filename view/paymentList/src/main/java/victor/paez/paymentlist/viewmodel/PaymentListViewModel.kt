package victor.paez.paymentlist.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import victor.paez.usecases.dashboard.GetPaymentListUseCase
import victor.paez.usecases.dashboard.model.PaymentListUI
import javax.inject.Inject

@HiltViewModel
class PaymentListViewModel @Inject constructor(
    private val getPaymentListUseCase: GetPaymentListUseCase,
) : ViewModel() {
    var paymentList = mutableStateOf(listOf<PaymentListUI>())
        private set

    var isLoading = mutableStateOf(true)
        private set

    fun getPaymentList(accountId: String) {
        isLoading.value = true
        viewModelScope.launch {
            getPaymentListUseCase.invoke(accountId).collect {
                isLoading.value = false
                paymentList.value = it
            }
        }
    }
}
