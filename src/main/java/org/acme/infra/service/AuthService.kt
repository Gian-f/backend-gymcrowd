package org.acme.infra.service

import jakarta.enterprise.context.ApplicationScoped
import org.acme.domain.dto.request.UserLoginRequest
import org.acme.domain.dto.response.AuthResponse
import org.acme.domain.dto.response.UserResponse
import org.acme.domain.exception.InvalidPasswordException
import org.acme.domain.exception.UnregisteredEmailException
import org.acme.infra.repository.UserRepository
import org.acme.infra.security.BCryptHashProvider
import org.acme.infra.security.JwtTokenProvider
import org.acme.utils.ResponseMessages.LOGIN_OK

@ApplicationScoped
class AuthService(
    private val repository: UserRepository,
    private val tokenProvider: JwtTokenProvider,
    private val hashProvider: BCryptHashProvider
) {
    fun login(userLoginRequest: UserLoginRequest): AuthResponse {
        val user = repository.findByEmail(userLoginRequest.email) ?: throw UnregisteredEmailException()

        if (!hashProvider.verify(userLoginRequest.password, user.password)) {
            throw InvalidPasswordException()
        }
        val token = tokenProvider.create(user.username, user.email)
        return AuthResponse.build(token, LOGIN_OK, true)
    }
}