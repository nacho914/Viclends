package victor.paez.addaccount.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import victor.paez.addaccount.R
import victor.paez.addaccount.viewmodel.AddAccountViewModel
import victor.paez.ui.BlockingLoadingWheel
import victor.paez.ui.MainAlertDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAccountScreen(
    padding: PaddingValues,
    clientId: String,
    changeTitle: (String) -> Unit,
    addAccountViewModel: AddAccountViewModel = hiltViewModel(),
) {
    changeTitle(stringResource(id = R.string.add_account_screen_name))
    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)

    addAccountViewModel.account.value.date = datePickerState.selectedDateMillis ?: 0L
    addAccountViewModel.account.value.idClient = clientId
    val isLoading = addAccountViewModel.isLoading.value
    val isAccountAdd = addAccountViewModel.isAccountAdd.value

    MainAlertDialog(
        showDialog = isAccountAdd,
        title = "Cuenta Agregada",
        textBody = "La cuenta ha sido agregada con exito",
        confirmText = "Ok",
    ) {
        addAccountViewModel.addConfirm()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TextField(
                    value = addAccountViewModel.nameAccount.value,
                    label = { Text(text = stringResource(id = R.string.name_account_holder)) },
                    modifier = Modifier.padding(16.dp),
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    onValueChange = {
                        addAccountViewModel.nameAccount.value = it
                    },
                )

                Text(
                    text = stringResource(id = R.string.creation_date_label),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start)
                        .padding(start = 32.dp, top = 16.dp),
                )
                DatePicker(
                    showModeToggle = false,
                    state = datePickerState,
                    title = null,
                    headline = null,
                    modifier = Modifier.padding(horizontal = 16.dp),
                )

                TextField(
                    value = addAccountViewModel.debtAccount.value,
                    onValueChange = { addAccountViewModel.debtAccount.value = it },
                    label = { Text(text = stringResource(id = R.string.debt_text_holder)) },
                    modifier = Modifier.padding(16.dp),
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    placeholder = { Text("0") },
                    prefix = { Text(text = "$") },
                )

                TextField(
                    value = addAccountViewModel.revenueAccount.value,
                    onValueChange = { addAccountViewModel.revenueAccount.value = it },
                    label = { Text(text = stringResource(id = R.string.revenue_text_holder)) },
                    modifier = Modifier.padding(16.dp),
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    placeholder = { Text("0") },
                    prefix = { Text(text = "$") },
                )

                Button(
                    onClick = { addAccountViewModel.addAccount() },
                    enabled = addAccountViewModel.isEnable(),
                    modifier = Modifier.padding(16.dp),
                ) {
                    Text(text = stringResource(id = R.string.add_account_button))
                }
            }
        }
    }

    if (isLoading) {
        BlockingLoadingWheel()
    }
}
