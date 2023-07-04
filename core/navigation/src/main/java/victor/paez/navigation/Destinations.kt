package victor.paez.navigation

sealed class Destinations(
    val route: String,
) {
    object DashboardScreen : Destinations("dashboardScreen")
    object ClientListScreen : Destinations("clientListScreen")
    object ClientDetailScreen : Destinations("clientDetailScreen/{$CLIENT_ID}") {
        fun createRoute(clientId: String) = "clientDetailScreen/$clientId"
    }
    object AccountListScreen : Destinations("accountListScreen/{$CLIENT_ID}") {
        fun createRoute(clientId: String) = "accountListScreen/$clientId"
    }

    object PaymentListScreen : Destinations("paymentListScreen/{$ACCOUNT_ID}") {
        fun createRoute(accountId: String) = "paymentListScreen/$accountId"
    }

    object ClientAddScreen : Destinations("clientAddScreen")

    object AccountAddScreen : Destinations("accountAddScreen/{$CLIENT_ID}") {
        fun createRoute(clientId: String) = "accountAddScreen/$clientId"
    }
}
