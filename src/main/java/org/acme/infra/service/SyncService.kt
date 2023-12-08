package org.acme.infra.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.acme.domain.dto.request.SyncGymRequest
import org.acme.domain.dto.response.GenericResponse
import org.acme.domain.exception.GenericException
import org.acme.domain.model.SyncGym
import org.acme.infra.repository.SyncRepository
import org.acme.utils.ResponseMessages.GENERIC_MESSAGE
import java.util.*
import kotlin.random.Random

@ApplicationScoped
class SyncService @Inject constructor(
    private val repository: SyncRepository
) {
    fun register(request: SyncGymRequest): GenericResponse {
        return try {
            val registry = request.toEntity()
            val frequencia = Random.nextDouble(1.0, 100.0)
            val treatedRegistry = SyncGym(
                academiaSincronizar = registry.academiaSincronizar,
                nomeRegistrado = registry.nomeRegistrado,
                numeroRegistrado = registry.numeroRegistrado,
                frequenciaUsuario = toPercentage(frequencia)
            )
            repository.persist(treatedRegistry)
            GenericResponse(
                result = treatedRegistry,
                message = GENERIC_MESSAGE,
                status = true
            )
        } catch (e: Exception) {
            throw GenericException("erro ao cadastrar sincronizar academia")
        }
    }

    fun toPercentage(valor: Double): String {
        val formatado = Formatter().format("%.1f%%", valor).toString()
        return formatado.replace(',', '.') // opcional: substituir vírgula por ponto se necessário
    }
}