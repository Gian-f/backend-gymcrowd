package org.acme.domain.exception

import jakarta.ws.rs.WebApplicationException
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.acme.domain.dto.response.ErrorResponse

class EmptyPasswordException : WebApplicationException(
    Response.status(Response.Status.NOT_ACCEPTABLE)
        .entity(ErrorResponse("Senha não pode ser vazia"))
        .type(MediaType.APPLICATION_JSON)
        .build()
)