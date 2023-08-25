package org.acme.presentation

import jakarta.annotation.security.PermitAll
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.core.Response.Status.OK
import jakarta.ws.rs.core.Response.ok
import org.acme.domain.dto.request.UserLoginRequest
import org.acme.infra.service.AuthService
import org.acme.utils.ValidationMessages
import org.eclipse.microprofile.openapi.annotations.tags.Tag


@Tag(name = "Auth Resource")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/v1/auth")

class AuthResource(
        private val authService: AuthService
) {
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    fun login(
            @Valid @NotNull(message = ValidationMessages.REQUEST_BODY_MUST_NOT_BE_NULL)
            userLoginRequest: UserLoginRequest
    ): Response = ok(authService.login(userLoginRequest)).status(OK).build()
}