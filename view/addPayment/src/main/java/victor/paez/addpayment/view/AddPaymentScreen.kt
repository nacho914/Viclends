package victor.paez.addpayment.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import victor.paez.addpayment.R
import victor.paez.addpayment.viewModel.AddPaymentViewModel
import victor.paez.ui.BlockingLoadingWheel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPaymentScreen(
    padding: PaddingValues,
    accountId: String,
    changeTitle: (String) -> Unit,
    addPaymentViewModel: AddPaymentViewModel = hiltViewModel(),
) {
    changeTitle(stringResource(id = R.string.add_payment_screen_name))
    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
    addPaymentViewModel.paymentAddUI.value.date = datePickerState.selectedDateMillis ?: 0L
    val isLoading: Boolean by addPaymentViewModel.isLoading

    LaunchedEffect(accountId) {
        addPaymentViewModel.getAccountInformation(accountId)
    }

    Column(
        modifier = Modifier.padding(padding).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Cantidad Pendiente:",
            modifier = Modifier.padding(16.dp),
        )

        Text(
            text = "$" + addPaymentViewModel.getTotalDebt() + ".00",
            modifier = Modifier.padding(8.dp),
        )

        TextField(
            value = addPaymentViewModel.paymentText.value,
            onValueChange = { addPaymentViewModel.paymentText.value = it },
            label = { Text(text = "Abono") },
            modifier = Modifier.padding(16.dp),
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            placeholder = { Text("0") },
            prefix = { Text(text = "$") },
        )

        DatePicker(
            showModeToggle = false,
            state = datePickerState,
            title = null,
            headline = null,
            modifier = Modifier.padding(horizontal = 16.dp),
        )

        Button(
            onClick = { addPaymentViewModel.addAccount() },
            enabled = addPaymentViewModel.isEnable(),
            modifier = Modifier.padding(16.dp),
        ) {
            Text(text = "Agregar abono")
        }
    }

    if (isLoading) {
        BlockingLoadingWheel()
    }
}
