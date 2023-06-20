package victor.paez.clientList.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import victor.paez.clientList.viewmodel.ClientListViewModel
import victor.paez.clientslist.R
import victor.paez.ui.FABAdd
import victor.paez.ui.LoadingWheel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientListScreen(
    padding: PaddingValues,
    changeTitle: (String) -> Unit,
    navClientDetail: (clientId: String) -> Unit,
    dashboardViewModel: ClientListViewModel = hiltViewModel(),
) {
    changeTitle(stringResource(id = R.string.screen_name))

    val clientList = dashboardViewModel.clientList.value
    val isLoading: Boolean by dashboardViewModel.isLoading

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        if (isLoading) {
            LoadingWheel()
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
        ) {
            items(clientList) { client ->
                Surface(
                    onClick = { navClientDetail(client.id) },
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
        FABAdd(padding = padding) {}
    }
}
