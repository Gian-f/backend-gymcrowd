package org.acme.infra.security

import io.smallrye.jwt.build.Jwt
import jakarta.enterprise.context.ApplicationScoped
import org.eclipse.microprofile.config.inject.ConfigProperty
import java.time.Instant.now
import java.time.temporal.ChronoUnit.MINUTES

@ApplicationScoped
class JwtTokenProvider(
    @ConfigProperty(name = "mp.jwt.verify.issuer")
    private val issuer: String,
    @ConfigProperty(name = "mp.jwt.expiration.time.minutes")
    private val expirationTimeInMinutes: Long,
) {
    fun create(username: String, email: String): String = Jwt.claims()
        .issuer(issuer)
        .subject(username)
        .subject(email)
        .issuedAt(now())
        .expiresAt(now().plus(expirationTimeInMinutes, MINUTES))
        .sign()
}
