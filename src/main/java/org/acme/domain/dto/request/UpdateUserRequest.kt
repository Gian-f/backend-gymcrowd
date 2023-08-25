package org.acme.domain.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import jdk.jfr.BooleanFlag
import org.acme.domain.model.User

@JsonRootName("users")
@RegisterForReflection
data class UpdateUserRequest(

        @JsonProperty("username")
        val username: String? = null,

        @JsonProperty("email")
        val email: String? = null,

        @JsonProperty("password")
        val password: String? = null,

        @JsonProperty("status")
        val status: Boolean? = null,
) {
    fun applyChangesTo(existingUser: User) = User(
            username = username ?: existingUser.username,
            email = email ?: existingUser.email,
            password = password ?: existingUser.password,
            status = status ?: existingUser.status
    )
}