package victor.paez.delay.model

import java.util.Date

data class AddDelayDTO(
    var idClient: String = "",
    var idAccount: String = "",
    var delay: Int = 0,
    var description: String = "",
    var date: Date? = null,
)
