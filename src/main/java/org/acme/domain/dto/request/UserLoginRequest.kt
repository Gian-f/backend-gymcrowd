package org.acme.domain.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName

@JsonRootName("users")
data class UserLoginRequest(

        @JsonProperty("email")
        val email: String,

        @JsonProperty("password")
        val password: String,
)
