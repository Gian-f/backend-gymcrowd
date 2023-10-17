package org.acme.domain.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.validation.constraints.NotNull
import org.acme.domain.model.Address
import org.acme.domain.model.Gym

@JsonRootName("gyms")
@RegisterForReflection
data class CreateGymRequest(

    @NotNull
    @JsonProperty("nome_academia")
    val nomeAcademia: String = "",

    @JsonProperty("endereco_sedes")
    val enderecoSedes: MutableList<Address> = mutableListOf(),

    @NotNull
    @JsonProperty("lotacao_media")
    val lotacaoMedia: Int = 0,

    ) {
    fun toEntity() = Gym(
        nomeAcademia = nomeAcademia,
        enderecoSedes = enderecoSedes,
        lotacaoMedia = lotacaoMedia,
    )
}

