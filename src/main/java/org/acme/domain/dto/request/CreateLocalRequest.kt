package org.acme.domain.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.validation.constraints.NotNull
import org.acme.domain.model.Local

@JsonRootName("local")
@RegisterForReflection
data class CreateLocalRequest(

    @NotNull
    @JsonProperty("nome_local")
    val nomeLocal: String = "",

    @JsonProperty("endereco_completo")
    val enderecoCompleto: String = "",

    ) {
    fun toEntity() = Local(
        nomeLocal = nomeLocal,
        enderecoCompleto = enderecoCompleto
    )
}
