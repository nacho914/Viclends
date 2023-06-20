package victor.paez.dashboard.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import victor.paez.dashboard.R
import victor.paez.dashboard.viewmodel.DashboardViewModel
import victor.paez.ui.LoadingWheel
import victor.paez.usecases.dashboard.model.DashboardData

@Composable
fun DashboardScreen(
    innerPadding: PaddingValues,
    changeTitle: (String) -> Unit,
    navClientList: () -> Unit,
    dashboardViewModel: DashboardViewModel = hiltViewModel(),
) {
    changeTitle(stringResource(id = R.string.app_name))

    val dashboardData: DashboardData by dashboardViewModel.dashboardData
    val isLoading: Boolean by dashboardViewModel.isLoading

    if (isLoading) {
        LoadingWheel()
    }

    Column(
        modifier = Modifier.padding(innerPadding).fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Total Prestamos nacho",
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = dashboardData.totalLends.toString(),
            )
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
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Total a recibir quincenalmente",
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = dashboardData.totalEveryWeek.toString(),
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Interes obtenido",
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = dashboardData.revenue.toString(),
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
