package org.acme.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection
import org.acme.domain.model.User
import java.util.*

@JsonRootName("users")
@RegisterForReflection
data class UserResponse(

        @JsonProperty("id")
        val id: Long,

        @JsonProperty("username")
        val username: String,

        @JsonProperty("email")
        val email: String,

        @JsonProperty("status")
        val status: Boolean,

        @JsonProperty("createdAt")
        val createdAt: Date?,

        @JsonProperty("modifiedAt")
        val modifiedAt: Date?
) {
    companion object {
        @JvmStatic
        fun build(user: User): UserResponse = UserResponse(
                id = user.id,
                username = user.username,
                email = user.email,
                status = user.status,
                createdAt = user.createdAt,
                modifiedAt = user.modifiedAt
        )
    }
}