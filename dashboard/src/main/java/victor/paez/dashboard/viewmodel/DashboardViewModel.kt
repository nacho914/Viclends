package victor.paez.dashboard.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import victor.paez.dashboard.model.DashboardData

class DashboardViewModel : ViewModel() {

    var dashboardData = mutableStateOf(DashboardData())
        private set
}
