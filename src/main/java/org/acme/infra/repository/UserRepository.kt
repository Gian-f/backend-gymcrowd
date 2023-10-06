package org.acme.infra.repository

import io.quarkus.hibernate.orm.panache.PanacheRepository
import io.quarkus.logging.Log
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import org.acme.domain.model.User

@ApplicationScoped
@Transactional
class UserRepository : PanacheRepository<User> {
    fun findByName(username: String): User? {
        return try {
            find("username", username).firstResult()
        } catch (e: Exception) {
            Log.error("exception", e)
            null
        }
    }

    fun findByEmail(email: String?): User? {
        return try {
            find("email", email).firstResult()
        } catch (e: Exception) {
            Log.error("exception", e)
            null
        }
    }

    fun findAllByStatus(): List<User> {
        return try {
            list("status", true)
        } catch (e: Exception) {
            Log.error("exception", e)
            emptyList()
        }
    }

    fun existsUsername(username: String?): Boolean {
        return try {
            count("username = ?1", username) > 0
        } catch (e: Exception) {
            Log.error("exception", e)
            false
        }
    }

    fun existsEmail(email: String?): Boolean {
        return try {
            count("email = ?1", email) > 0
        } catch (e: Exception) {
            Log.error("exception", e)
            false
        }
    }

    fun existsCpf(cpf: String?): Boolean {
        return try {
            count("cpf = ?1", cpf) > 0
        } catch (e: Exception) {
            Log.error("exception", e)
            false
        }
    }
}