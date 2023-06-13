package victor.paez.clientList.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import victor.paez.clientList.viewmodel.ClientListViewModel
import victor.paez.ui.LoadingWheel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientListScreen(
    dashboardViewModel: ClientListViewModel = hiltViewModel(),
) {
    val clientList = dashboardViewModel.clientList.value
    val isLoading: Boolean by dashboardViewModel.isLoading

    if (isLoading) {
        LoadingWheel()
    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(clientList) { client ->
            Surface(
                onClick = { },
                modifier = Modifier
                    .padding(8.dp),
                shape = RoundedCornerShape(4.dp),
                color = Color.LightGray,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(text = client.name)
                }
            }
        }
    }
}

@Preview
@Composable
fun ClientsListScreenPreview() {
    ClientListScreen()
}
