package org.acme.domain.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.acme.domain.model.Hire

@JsonRootName("cards")
@RegisterForReflection
data class HireRequest(

    @NotBlank(message = "Informe o nome da academia")
    @NotNull
    @JsonProperty("nome_academia")
    val nomeAcademia: String = "",

    @NotBlank(message = "Informe um cnpj válido")
    @NotNull
    @JsonProperty("cnpj_academia")
    val cnpjAcademia: String = "",

    @NotBlank(message = "Informe o email")
    @JsonProperty("email")
    val email: String = "",

    @NotNull
    @JsonProperty("numero_telefone")
    val numeroTelefone: String = "",

    @NotNull
    @JsonProperty("tipo_frequencia")
    val tipoFrequencia: String = "",

    @NotNull
    @JsonProperty("quantidade_academias")
    val quantidadeAcademias: Int = 0,

    ) {
    fun toEntity() = Hire(
        nomeAcademia = nomeAcademia,
        cnpjAcademia = cnpjAcademia,
        email = email,
        numeroTelefone = numeroTelefone,
        tipoFrequencia = tipoFrequencia,
        quantidadeAcademias = quantidadeAcademias
    )
}