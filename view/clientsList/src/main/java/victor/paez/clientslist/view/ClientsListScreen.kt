package victor.paez.clientslist.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ClientsListScreen() {
    val stringList = remember { mutableStateListOf("Elizabet Garcia", "Mario Castillo", "Anardis", "Gabriela Socorro") }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(stringList) { item ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(text = item)
            }
        }
    }
}

@Preview
@Composable
fun ClientsListScreenPreview() {
    ClientsListScreen()
}
