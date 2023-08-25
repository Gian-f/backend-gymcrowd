package org.acme.infra.service

import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.jwt.JsonWebToken


@ApplicationScoped
class JwtService(
        private val jwt: JsonWebToken
) {
    val email: String
        get() = jwt.getClaim("email")

    val username: String
        get() = jwt.getClaim("upn")

    val id: String
        get() = jwt.subject
}

