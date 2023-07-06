package victor.paez.usecases.model

import victor.paez.account.model.AddAccountDTO
import victor.paez.util.getCalendarTime

data class AccountAddUI(
    val id: String? = "",
    var idClient: String? = "",
    var name: String? = "",
    var debt: Int? = 0,
    var revenue: Int? = 0,
    var date: Long = 0L,
) {
    companion object {
        fun fromAccountAddUIToAccountAddDTO(
            accountAddUI: AccountAddUI,
        ): AddAccountDTO =
            AddAccountDTO(
                idClient = accountAddUI.idClient.orEmpty(),
                active = true,
                name = accountAddUI.name.orEmpty(),
                debt = accountAddUI.debt ?: 0,
                revenue = accountAddUI.revenue ?: 0,
                originalDebt = accountAddUI.debt ?: 0,
                originalRevenue = accountAddUI.revenue ?: 0,
                date = getCalendarTime(accountAddUI.date),
            )
    }
}
