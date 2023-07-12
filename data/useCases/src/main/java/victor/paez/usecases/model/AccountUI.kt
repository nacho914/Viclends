package victor.paez.usecases.model

import victor.paez.account.model.AccountDTO
import java.util.Date

data class AccountUI(
    val idAccount: String? = "",
    val active: Boolean? = false,
    val name: String? = "",
    val debt: Int = 0,
    val revenue: Int = 0,
    val date: Date? = null,
    val idClient: String? = "",
    val originalDebt: Int? = 0,
    val originalRevenue: Int? = 0,
) {
    companion object {
        fun fromAccountDtoToAccountUI(accountDTO: AccountDTO): AccountUI =
            AccountUI(
                idAccount = accountDTO.id,
                name = accountDTO.name.orEmpty(),
                debt = accountDTO.debt ?: 0,
                revenue = accountDTO.revenue ?: 0,
                date = accountDTO.date,
                active = accountDTO.active,
                idClient = accountDTO.idClient,
            )
    }
}
