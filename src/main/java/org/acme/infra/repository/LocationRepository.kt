package org.acme.infra.repository

import io.quarkus.hibernate.orm.panache.PanacheRepository
import io.quarkus.logging.Log
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import org.acme.domain.model.Location


@ApplicationScoped
@Transactional
class LocationRepository : PanacheRepository<Location> {
    fun fetchAll(): List<Location> {
        return try {
            listAll()
        } catch (e: Exception) {
            Log.error("exception", e)
            emptyList()
        }
    }
}