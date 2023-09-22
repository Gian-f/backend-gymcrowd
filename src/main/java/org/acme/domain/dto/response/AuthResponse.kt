package org.acme.domain.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection
import org.acme.domain.model.User

@JsonRootName("auth")
@RegisterForReflection
data class AuthResponse(

    @JsonProperty("token")
    val token: String,

    @JsonProperty("message")
    val message: String,

    @JsonProperty("status")
    val status: Boolean,
) {
    companion object {
        @JvmStatic
        fun build(token: String, message: String, status: Boolean): AuthResponse = AuthResponse(
            token = token,
            message = message,
            status = status,
        )
    }
}