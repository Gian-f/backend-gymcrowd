package org.acme.domain.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.ws.rs.core.Response
import org.acme.domain.model.Card

@JsonRootName("cards")
@RegisterForReflection
data class CardResponse(

    @JsonProperty("result")
    val result: Any?,

    @JsonProperty("message")
    val message: String,

    @JsonProperty("status")
    val status: Boolean,
) {
    companion object {
        @JvmStatic
        fun build(card: Card, message: String, status: Boolean): CardResponse = CardResponse(
            result = card,
            message = message,
            status = status
        )
    }
}
