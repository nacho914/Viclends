package victor.paez.usecases.model

import victor.paez.client.model.ClientAddDTO
import java.util.Date

data class ClientAddUI(
    var name: String = "",
    var phone: String = "",
    var birthday: Long = 0L,
    var startDate: Long = 0L,
) {
    companion object {
        fun fromClientAddUIToClientAddDTO(clientAddUI: ClientAddUI):
            ClientAddDTO =
            ClientAddDTO(
                name = clientAddUI.name,
                phone = clientAddUI.phone,
                birthday = Date(clientAddUI.birthday),
                startDate = Date(clientAddUI.startDate),
            )
    }
}
