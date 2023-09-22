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
import org.acme.utils.ResponseMessages.GENERIC_MESSAGE
import org.acme.utils.ResponseMessages.USER_CREATED
import org.acme.utils.ResponseMessages.USER_UPDATED

@ApplicationScoped
class UserService(
    private val repository: UserRepository,
    private val hashProvider: BCryptHashProvider
) {
    fun findById(id: Long): UserResponse {
        val user = repository.findById(id) ?: throw UserNotFoundException()
        return UserResponse.build(user, GENERIC_MESSAGE, true)
    }

    fun findAll(): List<UserResponse> {
        val users = repository.findAllByStatus()
        return users.map { list ->
            UserResponse.build(list, GENERIC_MESSAGE, true)
        }
    }

    fun register(newUser: CreateUserRequest): UserResponse {
        validateUsernameAndEmail(newUser.username, newUser.email)

        val hashedPassword = hashProvider.hash(newUser.password)
        val user = newUser.toEntity().apply { password = hashedPassword }

        repository.persist(user)
        return UserResponse.build(user, USER_CREATED, true)
    }

    fun update(loggedInUserId: Long, updateRequest: UpdateUserRequest): UserResponse {
        val user = repository.findById(loggedInUserId) ?: throw UserNotFoundException()

        validateUsernameAndEmail(updateRequest.username, updateRequest.email)

        if (!updateRequest.password.isNullOrEmpty()) {
            user.password = hashProvider.hash(updateRequest.password)
        }

        updateRequest.applyChangesTo(user)
        repository.persist(user)

        return UserResponse.build(user, USER_UPDATED, true)
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
