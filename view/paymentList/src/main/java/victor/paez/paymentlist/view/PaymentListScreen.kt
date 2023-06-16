package victor.paez.paymentlist.view

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
import victor.paez.paymentlist.viewmodel.PaymentListViewModel
import victor.paez.ui.LoadingWheel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentListScreen(
    accountId: String,
    paymentListViewModel: PaymentListViewModel = hiltViewModel(),
) {
    val paymentList by paymentListViewModel.paymentList
    val isLoading: Boolean by paymentListViewModel.isLoading

    LaunchedEffect(accountId) {
        paymentListViewModel.getPaymentList(accountId)
    }

    if (isLoading) {
        LoadingWheel()
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(paymentList) { payment ->
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
                    Text(text = payment.total.toString())
                }
            }
        }
    }
}
