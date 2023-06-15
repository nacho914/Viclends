package victor.paez.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import victor.paez.clientList.view.ClientListScreen
import victor.paez.clientdetail.view.ClientDetailScreen
import victor.paez.dashboard.view.DashboardScreen

@Composable
fun NavigationHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destinations.DashboardScreen.route) {
        composable(Destinations.DashboardScreen.route) {
            DashboardScreen(
                innerPadding = PaddingValues(16.dp),
                navClientList = { navController.navigate(Destinations.ClientListScreen.route) },
            )
        }

        composable(Destinations.ClientListScreen.route) {
            ClientListScreen(
                navClientDetail = { clientId ->
                    navController.navigate(Destinations.ClientDetailScreen.createRoute(clientId))
                },
            )
        }

        composable(Destinations.ClientDetailScreen.route) { backStackEntry ->
            ClientDetailScreen(backStackEntry.arguments?.getString(CLIENT_ID).orEmpty())
        }
    }
}