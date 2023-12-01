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
    open var registeredName: String = "",

    @field:NotBlank(message = ValidationMessages.REQUIRED)
    open var registeredNumber: String = "",

    @field:NotBlank(message = ValidationMessages.REQUIRED)
    open var gymSync: String = "",

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    open var createdAt: Date? = Date(),

    @UpdateTimestamp
    @Column(name = "modified_at", nullable = false)
    open var modifiedAt: Date? = Date()
)
