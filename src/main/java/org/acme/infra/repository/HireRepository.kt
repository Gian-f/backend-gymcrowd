package org.acme.infra.repository

import io.quarkus.hibernate.orm.panache.PanacheRepository
import io.quarkus.logging.Log
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import org.acme.domain.model.Hire

@ApplicationScoped
@Transactional
class HireRepository: PanacheRepository<Hire> {

    fun fetchAll(): List<Hire> {
        return try {
            find("status = true").list()
        } catch (e: Exception) {
            Log.error("exception", e)
            emptyList()
        }
    }
}