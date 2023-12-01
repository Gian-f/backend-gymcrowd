package org.acme.infra.repository

import io.quarkus.hibernate.orm.panache.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import org.acme.domain.model.SyncGym

@ApplicationScoped
@Transactional
class SyncRepository : PanacheRepository<SyncGym> {

}