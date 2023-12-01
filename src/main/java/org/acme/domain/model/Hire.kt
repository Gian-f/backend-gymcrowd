package org.acme.domain.model

import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.acme.utils.ValidationMessages
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*

@Entity(name = "Hire")
@RegisterForReflection
open class Hire(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long = 0,

    @Column(name = "nome_academia")
    @field:NotBlank(message = "Nome academia não pode ser vazio")
    open val nomeAcademia: String = "",

    @Column(name = "cnpj_academia")
    @field:NotBlank(message = "CNPJ não pode ser vazio")
    open val cnpjAcademia: String = "",

    @field:Email
    @field:NotBlank(message = ValidationMessages.EMAIL_MUST_BE_NOT_BLANK)
    @Column(unique = true)
    open val email: String = "",

    @Column(name = "numero_telefone")
    @field:NotBlank(message = "Numero Telefone não pode ser vazio")
    open val numeroTelefone: String = "",

    @Column(name = "tipo_frequencia")
    open val tipoFrequencia: String = "",

    @Column(name = "quantidade_academias")
    open val quantidadeAcademias: Int = 0,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    open val createdAt: Date? = Date(),

    @UpdateTimestamp
    @Column(name = "modified_at", nullable = false)
    open val modifiedAt: Date? = Date()
)
