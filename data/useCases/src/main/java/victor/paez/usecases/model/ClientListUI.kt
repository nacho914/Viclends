package victor.paez.usecases.model

import victor.paez.client.model.ClientDTO
import java.util.Date

data class ClientListUI(
    val id: String = "",
    val name: String = "",
    val debt: Int = 0,
    val revenue: Int = 0,
    val birthday: Date?,
    val imageUrl: String = "",
) {
    companion object {
        fun fromClientDtoToClientListUI(clientDTO: ClientDTO):
            ClientListUI =
            ClientListUI(
                id = clientDTO.id.orEmpty(),
                name = clientDTO.name.orEmpty(),
                debt = clientDTO.debt ?: 0,
                revenue = clientDTO.revenue ?: 0,
                birthday = clientDTO.birthday,
                imageUrl = clientDTO.imageUrl,
            )
    }
}
