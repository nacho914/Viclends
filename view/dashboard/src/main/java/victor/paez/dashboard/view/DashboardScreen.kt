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
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import victor.paez.dashboard.R
import victor.paez.dashboard.viewmodel.DashboardViewModel
import victor.paez.ui.LoadingWheel
import victor.paez.ui.MainDebtCard
import victor.paez.usecases.model.DashboardData
import victor.paez.util.Claret

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
        MainDebtCard(
            title = "Prestamos historicos",
            total = dashboardViewModel.getOriginalTotal(),
            debt = dashboardData.originalDebt,
            revenue = dashboardData.originalRevenue,
            delay = dashboardData.originalDelay,
        ) {
            Image(
                painter = painterResource(id = victor.paez.ui.R.drawable.baseline_history_24),
                contentDescription = "",
                modifier = Modifier.size(80.dp),
            )
        }

        // Present Card

        MainDebtCard(
            title = "Prestamos actuales",
            total = dashboardViewModel.getPresentTotal(),
            debt = dashboardData.debt,
            revenue = dashboardData.revenue,
            delay = dashboardData.delay,
        ) {
            Image(
                painter = painterResource(id = victor.paez.ui.R.drawable.baseline_present_24),
                contentDescription = "",
                modifier = Modifier.size(80.dp),
            )
        }

        Divider(color = Claret, thickness = 1.dp)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Personas con prestamos activos",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif,
                ),
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = dashboardData.people.toString() + " Personas",
            )
        }

        Divider(color = Claret, thickness = 1.dp)

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
