package victor.paez.dashboard.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import victor.paez.usecases.GetResumeDataUseCase
import victor.paez.usecases.model.DashboardData
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getResumeDataUseCase: GetResumeDataUseCase,
) : ViewModel() {
    var dashboardData = mutableStateOf(DashboardData())
        private set

    var isLoading = mutableStateOf(true)
        private set

    init {
        getDashboardData()
    }
    private fun getDashboardData() {
        isLoading.value = true
        viewModelScope.launch {
            getResumeDataUseCase.invoke().collect {
                isLoading.value = false
                dashboardData.value = it
            }
        }
    }
}
