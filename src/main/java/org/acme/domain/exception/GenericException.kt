package org.acme.domain.exception

import jakarta.ws.rs.WebApplicationException
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.acme.domain.dto.response.ErrorResponse

class GenericException(override val message: String) : WebApplicationException(
    Response.status(Response.Status.NOT_ACCEPTABLE)
        .entity(ErrorResponse(message))
        .type(MediaType.APPLICATION_JSON)
        .build()
)