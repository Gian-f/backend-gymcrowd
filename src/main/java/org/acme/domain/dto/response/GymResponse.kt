package org.acme.domain.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection
import org.acme.domain.model.Gym

@JsonRootName("gym")
@RegisterForReflection
data class GymResponse(
    @JsonProperty("result")
    val result: Any?,

    @JsonProperty("message")
    val message: String?,

    @JsonProperty("status")
    val status: Boolean,
) {
    companion object {
        @JvmStatic
        fun build(gym: Gym, message: String?, status: Boolean): GymResponse = GymResponse(
            result = gym,
            message = message,
            status = status
        )
    }
}