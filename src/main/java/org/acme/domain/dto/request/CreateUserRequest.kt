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
data class CreateUserRequest(

        @NotBlank(message = "Informe o nome")
        @NotNull
        @JsonProperty("username")
        val username: String? = null,

        @NotBlank(message = "Informe o email válido")
        @Email
        @NotNull
        @JsonProperty("email")
        val email: String? = null,

        @BooleanFlag
        @JsonProperty("status")
        val status: Boolean = true,

        @NotBlank(message = "Informe o cpf válido")
        @NotNull
        @JsonProperty("cpf")
        val cpf: String? = null,

        @NotBlank(message = "Informe a senha")
        @Size(min = 8, max = 15)
        @NotNull
        @JsonProperty("password")
        val password: String? = null,

        @NotBlank(message = "Informe a senha")
        @Size(min = 8, max = 15)
        @NotNull
        @JsonProperty("confirmPassword")
        val confirmPassword: String? = null,

        @BooleanFlag
        @JsonProperty("termsConditions")
        val termsConditions: Boolean? = false
) {
    fun toEntity() = User(
            username = username.orEmpty(),
            email = email.orEmpty(),
            cpf = cpf.orEmpty(),
            password = password.orEmpty(),
    )
}