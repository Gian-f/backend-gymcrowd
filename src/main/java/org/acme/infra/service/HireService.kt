package org.acme.infra.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.acme.domain.dto.request.HireRequest
import org.acme.domain.dto.response.HireResponse
import org.acme.infra.repository.HireRepository
import org.acme.utils.ResponseMessages

@ApplicationScoped
class HireService @Inject constructor(
    private val repository: HireRepository
) {
    fun create(request: HireRequest): HireResponse {
        return try {
            val hire = request.toEntity()
            repository.persist(hire)
            HireResponse(
                result = hire,
                message = ResponseMessages.GENERIC_MESSAGE,
                status = true
            )
        } catch (e: Exception) {
            HireResponse(
                result = null,
                message = "Error: ${e.message}",
                status = false
            )
        }
    }

    fun getAll(): HireResponse {
        val hire = repository.fetchAll()
        return HireResponse(
            result = hire,
            message = ResponseMessages.GENERIC_MESSAGE,
            status = true
        )
    }
}