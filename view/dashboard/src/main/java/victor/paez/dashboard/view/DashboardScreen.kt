package victor.paez.dashboard.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import victor.paez.dashboard.R
import victor.paez.dashboard.viewmodel.DashboardViewModel
import victor.paez.ui.LoadingWheel
import victor.paez.usecases.model.DashboardData
import victor.paez.util.LightPurple

@Composable
fun DashboardScreen(
    innerPadding: PaddingValues,
    changeTitle: (String) -> Unit,
    navClientList: () -> Unit,
    dashboardViewModel: DashboardViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        dashboardViewModel.getDashboardData()
    }

    changeTitle(stringResource(id = R.string.app_name))

    val dashboardData: DashboardData by dashboardViewModel.dashboardData
    val isLoading: Boolean by dashboardViewModel.isLoading

    if (isLoading) {
        LoadingWheel()
    }

    Column(
        modifier = Modifier.padding(innerPadding).fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        // Original Card
        Card(
            Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            shape = CardDefaults.elevatedShape,
            colors = CardDefaults.cardColors(containerColor = LightPurple),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Prestamos historicos",
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Column(
                    Modifier.weight(0.3f).padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Image(
                        painter = painterResource(id = victor.paez.ui.R.drawable.baseline_history_24),
                        contentDescription = "",
                        modifier = Modifier.size(80.dp),
                    )
                }
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.fillMaxWidth().padding(16.dp).weight(0.7f),
                ) {
                    Text(
                        text = "Total prestado: " + dashboardViewModel.getOriginalTotal().toString(),
                    )
                    Text(
                        text = "Deuda Original: " + dashboardData.originalDebt.toString(),
                    )
                    Text(
                        text = "Ganancia Original: " + dashboardData.originalRevenue.toString(),
                    )
                    Text(
                        text = "Retardo Original: " + dashboardData.originalDelay.toString(),
                    )
                }
            }
        }
        // Present Card
        Card(
            Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            shape = CardDefaults.elevatedShape,
            colors = CardDefaults.cardColors(containerColor = LightPurple),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Prestamos actuales",
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Column(
                    Modifier.weight(0.3f).padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Image(
                        painter = painterResource(id = victor.paez.ui.R.drawable.baseline_present_24),
                        contentDescription = "",
                        modifier = Modifier.size(80.dp),
                    )
                }
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.fillMaxWidth().padding(16.dp).weight(0.7f),
                ) {
                    Text(
                        text = "Total Actual: " + dashboardViewModel.getPresentTotal().toString(),
                    )
                    Text(
                        text = "Deuda Actual: " + dashboardData.debt.toString(),
                    )
                    Text(
                        text = "Ganancia Actual: " + dashboardData.revenue.toString(),
                    )
                    Text(
                        text = "Retardo Actual: " + dashboardData.delay.toString(),
                    )
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Personas con prestamos activos",
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = dashboardData.people.toString(),
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(onClick = { navClientList() }) {
                Text(text = "Listado de clientes")
            }
        }
    }
}
