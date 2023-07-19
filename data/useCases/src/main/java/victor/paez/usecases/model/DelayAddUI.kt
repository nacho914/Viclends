package victor.paez.usecases.model

import victor.paez.delay.model.AddDelayDTO
import victor.paez.util.getCalendarTime

data class DelayAddUI(
    var delay: Int = 0,
    var description: String = "",
    var date: Long = 0L,
) {
    companion object {
        fun fromDelayAddUIToAddDelayDTO(
            delay: DelayAddUI,
            account: AccountUI,
        ): AddDelayDTO =
            AddDelayDTO(
                idClient = account.idClient.toString(),
                idAccount = account.idAccount.toString(),
                delay = delay.delay,
                description = delay.description,
                date = getCalendarTime(delay.date),
            )
    }
}
