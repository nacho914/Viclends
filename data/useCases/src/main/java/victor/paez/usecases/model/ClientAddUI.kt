package victor.paez.usecases.model

import victor.paez.client.model.ClientAddDTO
import victor.paez.util.getCalendarTime

data class ClientAddUI(
    var name: String = "",
    var phone: String = "",
    var birthday: Long = 0L,
    var startDate: Long = 0L,
    var imageUrl: String = "",
) {
    companion object {
        fun fromClientAddUIToClientAddDTO(
            clientAddUI: ClientAddUI,
            imgUrl: String,
        ):
            ClientAddDTO =
            ClientAddDTO(
                name = clientAddUI.name,
                phone = clientAddUI.phone,
                birthday = getCalendarTime(clientAddUI.birthday),
                startDate = getCalendarTime(clientAddUI.startDate),
                imgUrl = imgUrl,
            )
    }
}
