package org.acme.domain.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@JsonRootName("address")
data class GetAddressRequest(
    @NotBlank(message = "Informe o endereço")
    @NotNull
    @JsonProperty("address")
    val address: String,
    )
