package victor.paez.client.model

import java.util.Date

data class ClientAddDTO(
    val name: String? = "",
    val phone: String? = "",
    val debt: Int? = 0,
    val revenue: Int? = 0,
    val birthday: Date? = null,
    val startDate: Date? = null,
)
