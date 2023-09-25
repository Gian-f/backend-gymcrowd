package org.acme.domain.exception

import jakarta.ws.rs.WebApplicationException
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.acme.domain.dto.response.ErrorResponse

class InvalidCredentialsException(override val message: String) : WebApplicationException(
    Response.status(Response.Status.BAD_REQUEST)
        .entity(ErrorResponse(message))
        .type(MediaType.APPLICATION_JSON)
        .build()
)