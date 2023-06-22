package victor.paez.addclient.view

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import victor.paez.addclient.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddClientScreen(
    padding: PaddingValues,
) {
    var name by remember {
        mutableStateOf("Carlos")
    }

    var phone by remember {
        mutableStateOf("")
    }

    val birthdayPickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
    val startDatePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)

    Column(
        modifier = Modifier.fillMaxSize().padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = stringResource(id = R.string.name_text)) },
            modifier = Modifier.padding(16.dp),
        )

        TextField(
            value = phone,
            onValueChange = { phone = it },
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
            onClick = { /*TODO*/ },
            enabled = false,
        ) {
            Text(text = "Agregar cliente")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun show() {
    AddClientScreen(padding = PaddingValues(16.dp))
}
