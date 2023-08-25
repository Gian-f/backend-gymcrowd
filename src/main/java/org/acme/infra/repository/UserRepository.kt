package org.acme.infra.repository

import io.quarkus.hibernate.orm.panache.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import org.acme.domain.model.User

@ApplicationScoped
@Transactional
class UserRepository : PanacheRepository<User> {
    fun findByName(username: String): User? {
        return find("username", username).firstResult()
    }

    fun findByEmail(email: String?): User? {
        return find("email", email).firstResult()
    }

    fun findAllByStatus(): List<User> {
        return list("status", true)
    }

    fun existsUsername(username: String?): Boolean {
        return count("username = ?1", username) > 0
    }

    fun existsEmail(email: String?): Boolean {
        return count("email = ?1", email) > 0
    }
}