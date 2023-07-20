package victor.paez.adddelay.view

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import victor.paez.adddelay.R
import victor.paez.adddelay.viewModel.AddDelayViewModel
import victor.paez.ui.BlockingLoadingWheel
import victor.paez.ui.MainAlertDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDelayScreen(
    padding: PaddingValues,
    accountId: String,
    changeTitle: (String) -> Unit,
    addDelayViewModel: AddDelayViewModel = hiltViewModel(),
) {
    changeTitle(stringResource(id = R.string.add_delay_screen_name))
    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
    addDelayViewModel.delay.value.date = datePickerState.selectedDateMillis ?: 0L

    LaunchedEffect(accountId) {
        addDelayViewModel.getAccountInformation(accountId)
    }

    MainAlertDialog(
        showDialog = addDelayViewModel.isDelayAdd.value,
        title = stringResource(id = R.string.add_delay_title_alert),
        textBody = stringResource(id = R.string.add_delay_body_alert),
        confirmText = stringResource(id = R.string.add_delay_confirm_alert),
    ) {
        addDelayViewModel.confirmDelay()
    }

    Column(
        modifier = Modifier.padding(padding).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.peding_amount_label),
            modifier = Modifier.padding(16.dp),
        )

        Text(
            text = "$" + addDelayViewModel.getTotalDebt() + ".00",
            modifier = Modifier.padding(8.dp),
        )

        TextField(
            value = addDelayViewModel.delayText.value,
            onValueChange = {
                if (addDelayViewModel.onlyNumbers(it)) {
                    addDelayViewModel.delayText.value = it
                }
            },
            label = { Text(text = stringResource(id = R.string.add_delay_text)) },
            modifier = Modifier.padding(16.dp),
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            placeholder = { Text("0") },
            prefix = { Text(text = "$") },
            singleLine = true,
        )

        TextField(
            value = addDelayViewModel.descriptionText.value,
            onValueChange = {
                addDelayViewModel.descriptionText.value = it
            },
            label = { Text(text = stringResource(id = R.string.add_description_text)) },
            modifier = Modifier.padding(16.dp),
            maxLines = 3,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
        )

        DatePicker(
            showModeToggle = false,
            state = datePickerState,
            title = null,
            headline = null,
            modifier = Modifier.padding(horizontal = 16.dp),
        )

        Button(
            onClick = { addDelayViewModel.addDelay() },
            enabled = addDelayViewModel.isEnable(),
            modifier = Modifier.padding(16.dp),
        ) {
            Text(text = stringResource(id = R.string.add_delay_button))
        }
    }

    if (addDelayViewModel.isLoading.value) {
        BlockingLoadingWheel()
    }
}
