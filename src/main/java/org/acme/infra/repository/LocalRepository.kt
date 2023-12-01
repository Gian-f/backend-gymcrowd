package org.acme.infra.repository

import io.quarkus.hibernate.orm.panache.PanacheRepository
import io.quarkus.logging.Log
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import org.acme.domain.model.Local

@ApplicationScoped
@Transactional
class LocalRepository : PanacheRepository<Local> {
    fun fetchAll(): List<Local> {
        return try {
            listAll()
        } catch (e: Exception) {
            Log.error("exception", e)
            emptyList()
        }
    }
}