package victor.paez.clientdetail.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import victor.paez.clientdetail.viewmodel.ClientDetailViewModel
import victor.paez.ui.LoadingWheel

@Composable
fun ClientDetailScreen(
    idClient: String,
    clientDetailViewModel: ClientDetailViewModel = hiltViewModel(),
) {
    val name = clientDetailViewModel.clientDetail.value.name
    val isLoading: Boolean by clientDetailViewModel.isLoading

    LaunchedEffect(idClient) {
        clientDetailViewModel.getClientDetail(idClient)
    }

    if (isLoading) {
        LoadingWheel()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Name:",
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = name,
            )
        }
    }
}
