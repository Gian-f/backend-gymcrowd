package org.acme.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection
import org.acme.domain.model.User

@JsonRootName("users")
@RegisterForReflection
data class UserResponse(

    @JsonProperty("result")
    val result: User?,

    @JsonProperty("message")
    val message: String,

    @JsonProperty("status")
    val status: Boolean,
) {
    companion object {
        @JvmStatic
        fun build(user: User, message: String, status: Boolean): UserResponse = UserResponse(
            result = user,
            message = message,
            status = status
        )
    }
}