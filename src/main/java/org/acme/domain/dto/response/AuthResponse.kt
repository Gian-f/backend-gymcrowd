package org.acme.domain.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection
import org.acme.domain.model.User

@JsonRootName("auth")
@RegisterForReflection
data class AuthResponse(

    @JsonProperty("access_token")
    val token: String,

    @JsonProperty("user")
    val user: User,

    @JsonProperty("message")
    val message: String,

    @JsonProperty("status")
    val status: Boolean,
) {
    companion object {
        @JvmStatic
        fun build(token: String, user: User, message: String, status: Boolean): AuthResponse = AuthResponse(
            token = token,
            user = user,
            message = message,
            status = status,
        )
    }
}