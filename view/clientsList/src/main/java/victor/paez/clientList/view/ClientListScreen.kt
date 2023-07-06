package victor.paez.clientList.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
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
    navAddClient: () -> Unit,
    clientListViewModel: ClientListViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        clientListViewModel.getClientList()
    }

    changeTitle(stringResource(id = R.string.screen_name))

    val clientList = clientListViewModel.clientList.value
    val isLoading: Boolean by clientListViewModel.isLoading

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
                    color = Color.Gray,
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        Column {
                            AsyncImage(
                                model = client.imageUrl,
                                contentDescription = "Image",
                                contentScale = ContentScale.Crop,
                                placeholder = painterResource(victor.paez.ui.R.drawable.baseline_image_24),
                                error = painterResource(victor.paez.ui.R.drawable.baseline_error_24),
                                modifier = Modifier
                                    .align(Alignment.Start)
                                    .size(125.dp)
                                    .padding(8.dp)
                                    .clip(CircleShape),
                            )
                        }
                        Column(modifier = Modifier.fillMaxWidth().align(Alignment.CenterVertically)) {
                            Text(
                                text = client.name,
                                modifier = Modifier.padding(8.dp),
                            )
                        }
                    }
                }
            }
        }
        FABAdd(padding = padding) {
            navAddClient()
        }
    }
}
