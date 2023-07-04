package victor.paez.clientdetail.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import victor.paez.clientdetail.viewmodel.ClientDetailViewModel
import victor.paez.ui.FABAdd
import victor.paez.ui.LoadingWheel
import victor.paez.ui.R

@Composable
fun ClientDetailScreen(
    padding: PaddingValues,
    clientId: String,
    navAccountList: (clientId: String) -> Unit,
    navReturn: () -> Unit,
    navAddAccount: (clientId: String) -> Unit,
    clientDetailViewModel: ClientDetailViewModel = hiltViewModel(),
) {
    val client = clientDetailViewModel.clientDetail.value
    val isLoading: Boolean by clientDetailViewModel.isLoading

    LaunchedEffect(clientId) {
        clientDetailViewModel.getClientDetail(clientId)
    }

    if (clientDetailViewModel.clientDeleted.value) {
        clientDetailViewModel.clientDeleted.value = false
        navReturn()
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(padding),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            AsyncImage(
                model = client.imageUrl,
                contentDescription = "Image",
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.baseline_image_24),
                error = painterResource(R.drawable.baseline_error_24),
                modifier = Modifier
                    .size(250.dp)
                    .clip(RoundedCornerShape(24.dp)),
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(id = victor.paez.clientdetail.R.string.name_detail),
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = client.name,
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(onClick = { navAccountList(clientId) }) {
                Text(text = stringResource(id = victor.paez.clientdetail.R.string.accounts_list))
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(onClick = {
                clientDetailViewModel.deleteClient(clientId = clientId)
            }) {
                Text(text = stringResource(id = victor.paez.clientdetail.R.string.delete_client))
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
