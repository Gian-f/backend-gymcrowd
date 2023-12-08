package org.acme.domain.model

import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*

@Entity(name = "location")
@RegisterForReflection
open class Location(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long = 0,

    @Column(name = "nome_local")
    @field:NotBlank(message = "Nome local não pode ser vazio")
    open val nomeLocal: String = "",

    @Column(name = "endereco_completo")
    @field:NotBlank(message = "Endereço Completo não pode ser vazio")
    open val enderecoCompleto: String = "",

    @Column(name = "latitude")
    @field:NotBlank(message = "Latitude não pode ser vazio")
    open val latitude: String = "",

    @Column(name = "longitude")
    @field:NotBlank(message = "Longitude não pode ser vazio")
    open val longitude: String = "",

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    open val createdAt: Date? = Date(),

    @UpdateTimestamp
    @Column(name = "modified_at", nullable = false)
    open val modifiedAt: Date? = Date()
)
