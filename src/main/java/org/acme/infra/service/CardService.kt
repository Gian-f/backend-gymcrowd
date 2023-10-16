package org.acme.infra.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.acme.domain.dto.request.CreateCardRequest
import org.acme.domain.dto.response.CardResponse
import org.acme.domain.exception.GenericException
import org.acme.infra.repository.CardRepository
import org.acme.utils.ResponseMessages.GENERIC_MESSAGE

@ApplicationScoped
class CardService @Inject constructor(
    private val repository: CardRepository
) {
    fun createCard(request: CreateCardRequest): CardResponse {
        return try {
            validateFields(request)
            val card = request.toEntity()
            repository.persist(card)
            CardResponse(
                result = card,
                message = GENERIC_MESSAGE,
                status = true
            )
        } catch (e: Exception) {
            CardResponse(
                result = null,
                message = "Error: ${e.message}",
                status = false
            )
        }
    }

    fun getAllCards(): CardResponse {
        val cards = repository.findAllCards()
        return CardResponse(
            result = cards,
            message = GENERIC_MESSAGE,
            status = true
        )
    }

    private fun validateFields(request: CreateCardRequest) {
        if (request.cardNumber.isEmpty()) throw GenericException("Número do cartão não pode ser vazio")
        if (request.nameHolder.isEmpty()) throw GenericException("Nome do titular não pode ser vazio")
        if (repository.existsCardNumber(request.cardNumber)) throw GenericException("Número do cartão já existe")
    }
}
