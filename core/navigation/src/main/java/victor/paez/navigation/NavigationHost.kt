package victor.paez.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import victor.paez.acountlist.view.AccountListScreen
import victor.paez.addaccount.view.AddAccountScreen
import victor.paez.addclient.view.AddClientScreen
import victor.paez.addpayment.view.AddPaymentScreen
import victor.paez.clientList.view.ClientListScreen
import victor.paez.clientdetail.view.ClientDetailScreen
import victor.paez.dashboard.view.DashboardScreen
import victor.paez.paymentlist.view.PaymentListScreen

@Composable
fun NavigationHost(
    padding: PaddingValues,
    changeTitle: (String) -> Unit,
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destinations.DashboardScreen.route) {
        composable(Destinations.DashboardScreen.route) {
            DashboardScreen(
                innerPadding = padding,
                changeTitle = changeTitle,
                navClientList = { navController.navigate(Destinations.ClientListScreen.route) },
            )
        }

        composable(Destinations.ClientListScreen.route) {
            ClientListScreen(
                padding,
                changeTitle,
                navClientDetail = { clientId ->
                    navController.navigate(Destinations.ClientDetailScreen.createRoute(clientId))
                },
                navAddClient = {
                    navController.navigate(Destinations.ClientAddScreen.route)
                },
            )
        }
        composable(Destinations.ClientDetailScreen.route) { backStackEntry ->
            ClientDetailScreen(
                padding = padding,
                changeTitle = changeTitle,
                clientId = backStackEntry.arguments?.getString(CLIENT_ID).orEmpty(),
                navReturn = { navController.popBackStack() },
                navAccountList = { clientId ->
                    navController.navigate(Destinations.AccountListScreen.createRoute(clientId))
                },
                navAddAccount = { clientId ->
                    navController.navigate(Destinations.AccountAddScreen.createRoute(clientId))
                },
            )
        }

        composable(Destinations.AccountListScreen.route) { backStackEntry ->
            AccountListScreen(
                padding = padding,
                changeTitle = changeTitle,
                clientId = backStackEntry.arguments?.getString(CLIENT_ID).orEmpty(),
                navPaymentList = { accountId ->
                    navController.navigate(Destinations.PaymentListScreen.createRoute(accountId))
                },
                navAddPayment = { accountId ->
                    navController.navigate(Destinations.PaymentAddScreen.createRoute(accountId))
                },
            )
        }

        composable(Destinations.PaymentListScreen.route) { backStackEntry ->
            PaymentListScreen(
                changeTitle = changeTitle,
                accountId = backStackEntry.arguments?.getString(ACCOUNT_ID).orEmpty(),
            )
        }

        composable(Destinations.ClientAddScreen.route) {
            AddClientScreen(
                padding = padding,
                changeTitle = changeTitle,
            )
        }

        composable(Destinations.AccountAddScreen.route) { backStackEntry ->
            AddAccountScreen(
                padding = padding,
                clientId = backStackEntry.arguments?.getString(CLIENT_ID).orEmpty(),
                changeTitle = changeTitle,
            )
        }

        composable(
            Destinations.PaymentAddScreen.route,
        ) { backStackEntry ->
            AddPaymentScreen(
                padding = padding,
                changeTitle = changeTitle,
                accountId = backStackEntry.arguments?.getString(ACCOUNT_ID).orEmpty(),
            )
        }
    }
}
