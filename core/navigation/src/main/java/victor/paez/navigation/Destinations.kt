package victor.paez.navigation

sealed class Destinations(
    val route: String,
) {
    object DashboardScreen : Destinations("dashboardScreen")
    object ClientListScreen : Destinations("clientListScreen")
}
