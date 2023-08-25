package org.acme.infra.service

import jakarta.enterprise.context.ApplicationScoped
import org.acme.domain.dto.request.CreateUserRequest
import org.acme.domain.dto.request.UpdateUserRequest
import org.acme.domain.dto.response.UserResponse
import org.acme.domain.exception.EmailAlreadyExistsException
import org.acme.domain.exception.UserNotFoundException
import org.acme.domain.exception.UsernameAlreadyExistsException
import org.acme.infra.repository.UserRepository
import org.acme.infra.security.BCryptHashProvider

@ApplicationScoped
class UserService(
        private val repository: UserRepository,
        private val hashProvider: BCryptHashProvider
) {
    fun findById(id: Long): UserResponse {
        val user = repository.findById(id) ?: throw UserNotFoundException()
        return UserResponse.build(user)
    }

    fun findAll(): List<UserResponse> {
        val users = repository.findAllByStatus()
        return users.map { list ->
                UserResponse.build(list)
        }
    }

    fun register(newUser: CreateUserRequest): UserResponse {
        validateUsernameAndEmail(newUser.username, newUser.email)

        val hashedPassword = hashProvider.hash(newUser.password)
        val user = newUser.toEntity().apply { password = hashedPassword }

        repository.persist(user)
        return UserResponse.build(user)
    }

    fun update(loggedInUserId: Long, updateRequest: UpdateUserRequest): UserResponse {
        val user = repository.findById(loggedInUserId) ?: throw UserNotFoundException()

        validateUsernameAndEmail(updateRequest.username, updateRequest.email)

        if (!updateRequest.password.isNullOrEmpty()) {
            user.password = hashProvider.hash(updateRequest.password)
        }

        updateRequest.applyChangesTo(user)
        repository.persist(user)

        return UserResponse.build(user)
    }

    private fun validateUsernameAndEmail(username: String?, email: String?) {
        if (!username.isNullOrEmpty() && repository.existsUsername(username)) {
            throw UsernameAlreadyExistsException()
        }
        if (!email.isNullOrEmpty() && repository.existsEmail(email)) {
            throw EmailAlreadyExistsException()
        }
    }
}
