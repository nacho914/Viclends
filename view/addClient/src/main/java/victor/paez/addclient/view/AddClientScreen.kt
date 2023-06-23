package victor.paez.addclient.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import victor.paez.addclient.R
import victor.paez.addclient.viewmodel.AddClientViewModel
import victor.paez.ui.LoadingWheel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddClientScreen(
    padding: PaddingValues,
    addClientViewModel: AddClientViewModel = hiltViewModel(),
) {
    var isLoading = addClientViewModel.isLoading.value

    val birthdayPickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
    val startDatePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)

    addClientViewModel.client.value.birthday = birthdayPickerState.selectedDateMillis ?: 0L
    addClientViewModel.client.value.startDate = startDatePickerState.selectedDateMillis ?: 0L

    if (isLoading) {
        LoadingWheel()
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(
            value = addClientViewModel.nameClient.value,
            onValueChange = {
                addClientViewModel.nameClient.value = it
            },
            label = { Text(text = stringResource(id = R.string.name_text)) },
            modifier = Modifier.padding(16.dp),
        )

        TextField(
            value = addClientViewModel.phoneClient.value,
            onValueChange = {
                addClientViewModel.phoneClient.value = it
            },
            label = { Text(text = stringResource(id = R.string.phone_text)) },
            modifier = Modifier.padding(16.dp),
        )

        Text(
            text = stringResource(id = R.string.birthday_text),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
                .padding(start = 32.dp, top = 16.dp),
        )
        DatePicker(
            showModeToggle = false,
            state = birthdayPickerState,
            title = null,
            headline = null,
            modifier = Modifier.padding(horizontal = 16.dp),
        )

        Text(
            text = stringResource(id = R.string.start_date_text),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
                .padding(start = 32.dp, top = 16.dp),
        )
        DatePicker(
            showModeToggle = false,
            state = startDatePickerState,
            title = null,
            headline = null,
            modifier = Modifier.padding(horizontal = 16.dp),

        )

        Button(
            onClick = { addClientViewModel.addClient() },
            enabled = addClientViewModel.isEnable(),
        ) {
            Text(text = "Agregar cliente")
        }
    }
}
