package victor.paez.clientdetail.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import victor.paez.clientdetail.viewmodel.ClientDetailViewModel
import victor.paez.ui.DetailClientCharacteristics
import victor.paez.ui.FABAdd
import victor.paez.ui.LoadingWheel
import victor.paez.ui.MainDebtCard
import victor.paez.ui.R

@Composable
fun ClientDetailScreen(
    padding: PaddingValues,
    changeTitle: (String) -> Unit,
    clientId: String,
    navAccountList: (clientId: String) -> Unit,
    navReturn: () -> Unit,
    navAddAccount: (clientId: String) -> Unit,
    clientDetailViewModel: ClientDetailViewModel = hiltViewModel(),
) {
    val client = clientDetailViewModel.clientDetail.value
    val isLoading: Boolean by clientDetailViewModel.isLoading

    changeTitle(stringResource(id = victor.paez.clientdetail.R.string.detail_client_screen))

    LaunchedEffect(clientId) {
        clientDetailViewModel.getClientDetail(clientId)
    }

    if (clientDetailViewModel.clientDeleted.value) {
        clientDetailViewModel.clientDeleted.value = false
        navReturn()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(padding),
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Column {
                    AsyncImage(
                        model = client.imageUrl,
                        contentDescription = "Image",
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(R.drawable.baseline_image_24),
                        error = painterResource(R.drawable.baseline_error_24),
                        modifier = Modifier
                            .size(140.dp)
                            .clip(RoundedCornerShape(24.dp)),
                    )
                }

                Column(
                    horizontalAlignment = Alignment.Start,
                ) {
                    DetailClientCharacteristics(
                        text = stringResource(id = victor.paez.clientdetail.R.string.name_detail),
                        value = client.name,
                    )

                    DetailClientCharacteristics(
                        text = stringResource(id = victor.paez.clientdetail.R.string.age_detail),
                        value = stringResource(id = victor.paez.clientdetail.R.string.years_client, client.age),
                    )

                    DetailClientCharacteristics(
                        text = stringResource(id = victor.paez.clientdetail.R.string.birthday_detail),
                        value = client.birthday,
                    )

                    DetailClientCharacteristics(
                        text = stringResource(id = victor.paez.clientdetail.R.string.phone_detail),
                        value = client.phone,
                    )
                }
            }

            // Historic data information
            MainDebtCard(
                title = "Prestamos historicos",
                total = clientDetailViewModel.getOriginalTotal(),
                debt = client.originalDebt,
                revenue = client.originalRevenue,
                delay = client.originalDelay,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_history_24),
                    contentDescription = "",
                    modifier = Modifier.size(80.dp),
                )
            }

            // Present data information
            MainDebtCard(
                title = "Prestamos actuales",
                total = clientDetailViewModel.getPresentTotal(),
                debt = client.debt,
                revenue = client.revenue,
                delay = client.delay,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_present_24),
                    contentDescription = "",
                    modifier = Modifier.size(80.dp),
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(onClick = { navAccountList(clientId) }) {
                    Text(text = stringResource(id = victor.paez.clientdetail.R.string.accounts_list))
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(onClick = {
                    clientDetailViewModel.deleteClient(clientId = clientId)
                }) {
                    Text(text = stringResource(id = victor.paez.clientdetail.R.string.delete_client))
                }
            }
        }
    }

    FABAdd(padding = padding) {
        navAddAccount(clientId)
    }

    if (isLoading) {
        LoadingWheel()
    }
}
