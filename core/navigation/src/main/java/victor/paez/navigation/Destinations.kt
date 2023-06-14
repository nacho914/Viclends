package victor.paez.navigation

sealed class Destinations(
    val route: String,
) {
    object DashboardScreen : Destinations("dashboardScreen")
    object ClientListScreen : Destinations("clientListScreen")
    object ClientDetailScreen : Destinations("clientDetailScreen/{$CLIENT_ID}") {
        fun createRoute(clientId: String) = "clientDetailScreen/$clientId"
    }
}
