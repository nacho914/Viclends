package victor.paez.usecases.dashboard.model

import victor.paez.account.model.AccountDTO
import java.util.Date

data class AccountListUI(
    val id: String? = "",
    val active: Boolean? = false,
    val name: String? = "",
    val debt: Int? = 0,
    val revenue: Int? = 0,
    val date: Date? = null,
) {
    companion object {
        fun fromAccountDtoToAccountListUI(accountDTO: AccountDTO): AccountListUI =
            AccountListUI(
                id = accountDTO.id.orEmpty(),
                name = accountDTO.name.orEmpty(),
                debt = accountDTO.debt ?: 0,
                revenue = accountDTO.revenue ?: 0,
                date = accountDTO.date,
            )
    }
}
