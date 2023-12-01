package org.acme.infra.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.acme.domain.dto.request.CreateLocalRequest
import org.acme.domain.dto.response.GenericResponse
import org.acme.infra.repository.LocalRepository
import org.acme.utils.ResponseMessages

@ApplicationScoped
class LocalService @Inject constructor(
    private val repository: LocalRepository
) {
    fun register(request: CreateLocalRequest): GenericResponse {
        return try {
            val registry = request.toEntity()
            repository.persist(registry)
            val list = repository.fetchAll()
            GenericResponse(
                result = list,
                message = ResponseMessages.GENERIC_MESSAGE,
                status = true
            )
        } catch (e: Exception) {
            GenericResponse(
                result = e.message,
                message = ResponseMessages.GENERIC_MESSAGE,
                status = true
            )
        }
    }
}