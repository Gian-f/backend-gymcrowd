package org.acme.infra.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.acme.domain.dto.request.CreateGymRequest
import org.acme.domain.dto.response.GymResponse
import org.acme.domain.exception.GenericException
import org.acme.infra.repository.GymRepository
import org.acme.utils.ResponseMessages

@ApplicationScoped
class GymService
@Inject constructor(
    private val repository: GymRepository
) {
    fun register(request: CreateGymRequest): GymResponse {

        validateFields(request)
        val gym = request.toEntity()
        repository.persistAndFlush(gym)
        return GymResponse(
            result = gym,
            message = ResponseMessages.GENERIC_MESSAGE,
            status = true
        )
    }

    private fun validateFields(request: CreateGymRequest) {
        if (repository.existsGym(request.nomeAcademia)) throw GenericException("Academia já se encontra cadastrada")
        if (request.nomeAcademia.isEmpty()) throw GenericException("Por favor, digite o nome da academia")
        if (request.enderecoSedes.isEmpty()) throw GenericException("Por favor, forneça o endereço das sedes")
    }


    fun getAllGyms(): GymResponse {
        val gyms = repository.findAllGyms()
        return GymResponse(
            result = gyms,
            message = ResponseMessages.GENERIC_MESSAGE,
            status = true
        )
    }
}
