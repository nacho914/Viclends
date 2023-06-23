package victor.paez.usecases.model

import victor.paez.client.model.ClientDTO
import java.util.Date

data class ClientUI(
    val id: String = "",
    val name: String = "",
    val debt: Int = 0,
    val revenue: Int = 0,
    val birthday: Date? = null,
) {
    companion object {
        fun fromClientDtoToClientUI(clientDTO: ClientDTO):
            ClientUI =
            ClientUI(
                id = clientDTO.id.orEmpty(),
                name = clientDTO.name.orEmpty(),
                debt = clientDTO.debt ?: 0,
                revenue = clientDTO.revenue ?: 0,
                birthday = clientDTO.birthday,
            )
    }
}
