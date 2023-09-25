package org.acme.domain.exception

import jakarta.ws.rs.WebApplicationException
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.acme.domain.dto.response.ErrorResponse

class UserNotFoundException : WebApplicationException(
    Response.status(Response.Status.NOT_FOUND)
        .entity(ErrorResponse("Usuário não encontrado"))
        .type(MediaType.APPLICATION_JSON)
        .build()
)