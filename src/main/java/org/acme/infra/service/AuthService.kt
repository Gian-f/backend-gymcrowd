package org.acme.infra.service

import jakarta.enterprise.context.ApplicationScoped
import org.acme.domain.dto.request.UserLoginRequest
import org.acme.domain.dto.response.AuthResponse
import org.acme.domain.exception.InvalidCredentialsException
import org.acme.domain.exception.UserNotFoundException
import org.acme.infra.repository.UserRepository
import org.acme.infra.security.BCryptHashProvider
import org.acme.infra.security.JwtTokenProvider
import org.acme.utils.ResponseMessages.LOGIN_OK
import org.acme.utils.ValidationMessages.INVALID_CREDENTIALS

@ApplicationScoped
class AuthService(
    private val repository: UserRepository,
    private val tokenProvider: JwtTokenProvider,
    private val hashProvider: BCryptHashProvider
) {
    fun login(userLoginRequest: UserLoginRequest): AuthResponse {
        val user = when {
            userLoginRequest.login.contains('@') -> repository.findByEmail(userLoginRequest.login)
            else -> repository.findByName(userLoginRequest.login)
        } ?: throw UserNotFoundException()

        if (!hashProvider.verify(userLoginRequest.password, user.password)) {
            throw InvalidCredentialsException(INVALID_CREDENTIALS)
        }

        val token = tokenProvider.create(user.username)
        return AuthResponse.build(token, LOGIN_OK, true)
    }
}