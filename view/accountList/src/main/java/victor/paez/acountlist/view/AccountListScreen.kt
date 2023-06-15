package victor.paez.acountlist.view

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import victor.paez.acountlist.viewmodel.AccountListViewModel
import victor.paez.ui.LoadingWheel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountListScreen(
    clientId: String,
    accountListViewModel: AccountListViewModel = hiltViewModel(),
) {
    val dashboardData by accountListViewModel.accountList
    val isLoading: Boolean by accountListViewModel.isLoading

    LaunchedEffect(clientId) {
        accountListViewModel.getAccountList(clientId)
    }

    if (isLoading) {
        LoadingWheel()
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(dashboardData) { account ->
            Surface(
                onClick = {},
                modifier = Modifier.padding(8.dp),
                shape = RoundedCornerShape(4.dp),
                color = Color.LightGray,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(text = account.name.orEmpty())
                }
            }
        }
    }
}
