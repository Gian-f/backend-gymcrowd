package org.acme.domain.exception

import jakarta.ws.rs.WebApplicationException
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.acme.domain.dto.response.ErrorResponse

class UnregisteredEmailException : WebApplicationException(
    Response.status(Response.Status.NOT_FOUND)
        .entity(ErrorResponse("Email nao vinculado a nenhum usuario!"))
        .type(MediaType.APPLICATION_JSON)
        .build()
)