package victor.paez.usecases.model

import victor.paez.client.model.ClientDTO
import victor.paez.util.getFormattedDate
import victor.paez.util.getYearsOld

data class ClientUI(
    val id: String = "",
    val name: String = "",
    val debt: Int = 0,
    val revenue: Int = 0,
    val delay: Int = 0,
    val originalDebt: Int = 0,
    val originalRevenue: Int = 0,
    val originalDelay: Int = 0,
    val birthday: String = "",
    val imageUrl: String = "",
    val phone: String = "",
    val age: Int = 0,
) {
    companion object {
        fun fromClientDtoToClientUI(clientDTO: ClientDTO):
            ClientUI =
            ClientUI(
                id = clientDTO.id.orEmpty(),
                name = clientDTO.name.orEmpty(),
                debt = clientDTO.debt ?: 0,
                revenue = clientDTO.revenue ?: 0,
                delay = clientDTO.delay ?: 0,
                birthday = getFormattedDate(clientDTO.birthday),
                imageUrl = clientDTO.imageUrl,
                originalDebt = clientDTO.originalDebt ?: 0,
                originalRevenue = clientDTO.originalRevenue ?: 0,
                originalDelay = clientDTO.originalDelay ?: 0,
                phone = clientDTO.phone.orEmpty(),
                age = getYearsOld(clientDTO.birthday),

            )
    }
}
