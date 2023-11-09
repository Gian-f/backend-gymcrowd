package org.acme.infra.repository

import io.quarkus.hibernate.orm.panache.PanacheRepository
import io.quarkus.logging.Log
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import org.acme.domain.model.Card
import org.acme.domain.model.User

@ApplicationScoped
@Transactional
class CardRepository : PanacheRepository<Card> {

    fun existsCardNumber(cardNumber: String): Boolean {
        return try {
            count("cardNumber = ?1", cardNumber) > 0
        } catch (e: Exception) {
            Log.error("exception", e)
            false
        }
    }

    fun findAllCards(): List<Card> {
        return try {
            find("status = true").list()
        } catch (e: Exception) {
            Log.error("exception", e)
            emptyList()
        }
    }
}