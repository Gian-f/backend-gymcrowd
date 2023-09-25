package org.acme.domain.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@JsonRootName("users")
data class UserLoginRequest(

        @Size(min = 8, max = 15)
        @JsonProperty("login")
        val login: String,

        @NotBlank(message = "Informe a senha")
        @Size(min = 8, max = 15)
        @NotNull
        @JsonProperty("password")
        val password: String,

        @JsonProperty("remember_me")
        val rememberMe: Boolean
)