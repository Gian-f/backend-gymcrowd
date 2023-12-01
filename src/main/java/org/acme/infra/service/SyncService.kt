package org.acme.infra.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.acme.domain.dto.request.SyncGymRequest
import org.acme.domain.dto.response.GenericResponse
import org.acme.infra.repository.SyncRepository
import org.acme.utils.ResponseMessages.GENERIC_MESSAGE

@ApplicationScoped
class SyncService @Inject constructor(
    private val repository: SyncRepository
) {
    fun register(request: SyncGymRequest): GenericResponse {
        return try {
            val registry = request.toEntity()
            repository.persist(registry)
            GenericResponse(
                result = registry,
                message = GENERIC_MESSAGE,
                status = true
            )
        } catch (e: Exception) {
            GenericResponse(
                result = e.message,
                message = GENERIC_MESSAGE,
                status = true
            )
        }
    }
}