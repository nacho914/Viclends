package victor.paez.usecases.dashboard.model

import victor.paez.client.model.ClientDTO
import java.util.Date

data class ClientListUI(
    val name: String = "",
    val debt: Int = 0,
    val revenue: Int = 0,
    val birthday: Date?,
) {
    companion object {
        fun fromClientDtoToClientListUI(clientDTO: ClientDTO):
            ClientListUI =
            ClientListUI(
                name = clientDTO.name,
                debt = clientDTO.debt,
                revenue = clientDTO.revenue,
                birthday = clientDTO.birthday,
            )
    }
}
