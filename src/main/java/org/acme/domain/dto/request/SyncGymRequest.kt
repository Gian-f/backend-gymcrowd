package org.acme.domain.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.validation.constraints.NotNull
import org.acme.domain.model.SyncGym

@JsonRootName("sync")
@RegisterForReflection
data class SyncGymRequest(

    @NotNull
    @JsonProperty("nome_registrado")
    val nomeRegistrado: String = "",

    @NotNull
    @JsonProperty("numero_registrado")
    val numeroRegistrado: String = "",

    @JsonProperty("academia_sincronizar")
    val academiaSincronizar: String = "",

    ) {
    fun toEntity() = SyncGym(
        nomeRegistrado = nomeRegistrado,
        numeroRegistrado = numeroRegistrado,
        academiaSincronizar = academiaSincronizar
    )
}
