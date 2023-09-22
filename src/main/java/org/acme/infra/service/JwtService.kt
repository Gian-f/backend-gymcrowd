package org.acme.infra.service

import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.jwt.JsonWebToken
import java.util.logging.Logger


@ApplicationScoped
class JwtService(
    private val jwt: JsonWebToken
) {
    private val logger = Logger.getLogger(JwtService::class.java.name)

    val email: String
        get() = jwt.getClaim("email")

    val username: String
        get() = jwt.getClaim("upn")

    val id: String
        get() = jwt.subject
}