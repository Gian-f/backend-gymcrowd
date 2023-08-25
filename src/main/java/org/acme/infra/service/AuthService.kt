package org.acme.infra.service

import jakarta.enterprise.context.ApplicationScoped
import org.acme.domain.dto.request.UserLoginRequest
import org.acme.domain.dto.response.UserResponse
import org.acme.domain.exception.InvalidPasswordException
import org.acme.domain.exception.UnregisteredEmailException
import org.acme.infra.repository.UserRepository
import org.acme.infra.security.BCryptHashProvider

@ApplicationScoped
class AuthService(
        private val repository: UserRepository,
        private val hashProvider: BCryptHashProvider
) {

    fun login(userLoginRequest: UserLoginRequest): UserResponse {
        val user = repository.findByEmail(userLoginRequest.email) ?: throw UnregisteredEmailException()

        if (!hashProvider.verify(userLoginRequest.password, user.password)) {
            throw InvalidPasswordException()
        }
        return UserResponse.build(user)
    }
}