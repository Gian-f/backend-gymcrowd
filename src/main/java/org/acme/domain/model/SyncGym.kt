package org.acme.domain.model

import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import org.acme.utils.ValidationMessages
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*

@Entity(name = "SyncGym")
@RegisterForReflection
open class SyncGym(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long = 0,

    @field:NotBlank(message = ValidationMessages.REQUIRED)
    @Column(name = "nome_registrado")
    open var nomeRegistrado: String = "",

    @field:NotBlank(message = ValidationMessages.REQUIRED)
    @Column(name = "numero_registrado")
    open var numeroRegistrado: String = "",

    @field:NotBlank(message = ValidationMessages.REQUIRED)
    @Column(name = "academia_sincronizar")
    open var academiaSincronizar: String = "",

    @Column(name = "forma_pagamento")
    open var formaPagamento: String = "Cartão de crédito",

    @Column(name = "assinatura")
    open var assinatura: String = "Plano Premium",

    @Column(name = "frequencia_usuario")
    open var frequenciaUsuario: String = "0%",

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    open var createdAt: Date? = Date(),

    @UpdateTimestamp
    @Column(name = "modified_at", nullable = false)
    open var modifiedAt: Date? = Date()
)
