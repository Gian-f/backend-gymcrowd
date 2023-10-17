package org.acme.domain.model

import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.annotations.UpdateTimestamp
import org.hibernate.type.SqlTypes
import java.util.*

@Entity(name = "Gym")
@RegisterForReflection
open class Gym(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long = 0,

    @Column(name = "nome_academia")
    open val nomeAcademia: String = "",

    @Column(name = "lotacao_media")
    open val lotacaoMedia: Int = 0,

    @Column(name = "endereco_sedes")
    @JdbcTypeCode(SqlTypes.JSON)
    open val enderecoSedes: MutableList<Address> = mutableListOf(),

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    open val createdAt: Date? = Date(),

    @UpdateTimestamp
    @Column(name = "modified_at", nullable = false)
    open val modifiedAt: Date? = Date(),
)