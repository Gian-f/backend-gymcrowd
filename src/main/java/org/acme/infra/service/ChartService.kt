package org.acme.infra.service

import jakarta.enterprise.context.ApplicationScoped
import org.acme.domain.dto.response.GenericResponse
import org.acme.utils.ResponseMessages.GENERIC_MESSAGE

@ApplicationScoped
class ChartService {
    fun homeChart(): GenericResponse {
        val randomNumbers = (1..7).map { (0..99).random() }
        return GenericResponse(
            result = randomNumbers,
            message = GENERIC_MESSAGE,
            status = true
        )
    }

    fun filterChart(): GenericResponse {
        val randomNumbers = (1..9).map { (0..99).random() }
        return GenericResponse(
            result = randomNumbers,
            message = GENERIC_MESSAGE,
            status = true
        )
    }
}