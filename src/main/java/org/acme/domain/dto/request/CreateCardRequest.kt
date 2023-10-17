package org.acme.domain.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.acme.domain.model.Card
import java.util.*

@JsonRootName("cards")
@RegisterForReflection
data class CreateCardRequest(

    @NotBlank(message = "Informe o nome do titular")
    @NotNull
    @JsonProperty("nameHolder")
    val nameHolder: String = "",

    @NotBlank(message = "Informe o número de cartão válido")
    @NotNull
    @JsonProperty("cardNumber")
    val cardNumber: String = "",

    @NotBlank(message = "Informe o a bandeira do cartão")
    @JsonProperty("flag")
    val flag: String = "",

    @NotNull
//    @field:NotBlank(message = ValidationMessages.CARD_DUE_MUST_BE_NOT_BLANK)
    @JsonProperty("dueDate")
    val dueDate: String = "",

    ) {
    fun toEntity() = Card(
        nameHolder = nameHolder,
        cardNumber = cardNumber,
        flag = flag,
        dueDate = dueDate,
    )
}