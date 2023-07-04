package victor.paez.addaccount.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AddAccountViewModel : ViewModel() {

    var nameAccount = mutableStateOf("")
        private set

    var debtAccount = mutableStateOf("")
        private set

    var revenueAccount = mutableStateOf("")
        private set
}
