package victor.paez.acountlist.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import victor.paez.acountlist.R
import victor.paez.acountlist.viewmodel.AccountListViewModel
import victor.paez.ui.BlockingLoadingWheel
import victor.paez.ui.LoadingWheel
import victor.paez.ui.MainAlertDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountListScreen(
    padding: PaddingValues,
    changeTitle: (String) -> Unit,
    clientId: String,
    navPaymentList: (accountId: String) -> Unit,
    navAddPayment: (accountId: String) -> Unit,
    navAddDelay: (accountId: String) -> Unit,
    accountListViewModel: AccountListViewModel = hiltViewModel(),
) {
    val accountList by accountListViewModel.accountList
    val isLoading: Boolean by accountListViewModel.isLoading
    val isAccountDeleted: Boolean by accountListViewModel.isAccountDeleted
    val isDeleting: Boolean by accountListViewModel.isDeleting
    changeTitle(stringResource(id = R.string.account_list_screen))

    LaunchedEffect(clientId) {
        accountListViewModel.getAccountList(clientId)
    }

    if (isLoading) {
        LoadingWheel()
    }

    MainAlertDialog(
        showDialog = isAccountDeleted,
        title = "Cuenta eliminada",
        textBody = "La cuenta ha sido eliminada con exito",
        confirmText = "OK",
    ) {
        accountListViewModel.getAccountList(clientId)
        accountListViewModel.isAccountDeleted.value = false
    }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(padding)) {
        items(accountList) { account ->
            Surface(
                onClick = { navPaymentList(account.id.orEmpty()) },
                modifier = Modifier.padding(8.dp),
                shape = RoundedCornerShape(4.dp),
                color = Color.LightGray,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxHeight().weight(1f),
                    ) {
                        Text(
                            text = account.name.orEmpty(),
                            color = Color.Black,
                        )
                    }

                    Column(
                        modifier = Modifier
                            .wrapContentWidth()
                            .weight(0.2f),
                    ) {
                        IconButton(onClick = {
                            navAddDelay(account.id.orEmpty())
                        }) {
                            Icon(
                                painter = painterResource(id = victor.paez.ui.R.drawable.sharp_more_time_24),
                                contentDescription = "Add",
                                tint = Color.Black,
                            )
                        }
                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier
                            .wrapContentWidth()
                            .weight(0.2f)
                            .padding(start = 8.dp),
                    ) {
                        IconButton(onClick = {
                            navAddPayment(account.id.orEmpty())
                        }) {
                            Icon(
                                painter = painterResource(id = victor.paez.ui.R.drawable.sharp_payments_24),
                                contentDescription = "Add",
                                tint = Color.Black,
                            )
                        }
                    }

                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier
                            .wrapContentWidth()
                            .weight(0.2f)
                            .padding(start = 8.dp),
                    ) {
                        IconButton(onClick = {
                            accountListViewModel.deleteAccount(account.id.orEmpty())
                        }) {
                            Icon(
                                painter = painterResource(id = victor.paez.ui.R.drawable.baseline_delete_24),
                                contentDescription = "Delete account",
                                tint = Color.Black,
                            )
                        }
                    }
                }
            }
        }
    }

    if (isDeleting) {
        BlockingLoadingWheel()
    }
}
