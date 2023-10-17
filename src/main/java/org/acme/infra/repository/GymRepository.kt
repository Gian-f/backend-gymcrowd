package org.acme.infra.repository

import io.quarkus.hibernate.orm.panache.PanacheRepository
import io.quarkus.logging.Log
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import org.acme.domain.model.Gym

@ApplicationScoped
@Transactional
class GymRepository : PanacheRepository<Gym> {
    fun findAllGyms(): List<Gym> {
        return try {
            listAll()
        } catch (e: Exception) {
            Log.error("exception", e)
            emptyList()
        }
    }

    fun existsGym(gym: String?): Boolean {
        return try {
            count("nomeAcademia = ?1", gym) > 0
        } catch (e: Exception) {
            Log.error("exception", e)
            false
        }
    }
}