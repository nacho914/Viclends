package victor.paez.account.model

import java.util.Date

data class AddAccountDTO(
    val idClient: String = "",
    val active: Boolean = false,
    val name: String = "",
    val debt: Int = 0,
    val revenue: Int = 0,
    val originalDebt: Int = 0,
    val originalRevenue: Int = 0,
    val date: Date? = null,
)
