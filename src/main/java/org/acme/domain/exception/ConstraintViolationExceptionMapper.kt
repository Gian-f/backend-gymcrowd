package org.acme.domain.exception

import jakarta.validation.ConstraintViolationException
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider
import org.acme.domain.dto.response.ErrorResponse

@Provider
class ConstraintViolationExceptionMapper : ExceptionMapper<ConstraintViolationException> {
    override fun toResponse(exception: ConstraintViolationException): Response {
        val message = exception.constraintViolations.joinToString(", ") { it.message }
        val response = ErrorResponse(message)
        return Response.status(Response.Status.BAD_REQUEST)
            .entity(response)
            .type(MediaType.APPLICATION_JSON)
            .build()
    }
}