package org.acme.domain.exception

import jakarta.ws.rs.WebApplicationException
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.acme.domain.dto.response.ErrorResponse

class UsernameAlreadyExistsException : WebApplicationException(
    Response.status(Response.Status.CONFLICT)
        .entity(ErrorResponse("Usuario já existe!"))
        .type(MediaType.APPLICATION_JSON)
        .build()
)