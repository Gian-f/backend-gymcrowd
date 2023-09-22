package org.acme.domain.model

import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*

@Entity(name = "Address")
@RegisterForReflection
@Access(AccessType.FIELD)
open class Address(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        open val id: Long = 0,

        open val rua: String = "",
        open val numero: String = "",
        open val cidade: String = "",
        open val cep: String = "",

        @CreationTimestamp
        @Column(name = "created_at", nullable = false, updatable = false)
        open val createdAt: Date? = Date(),

        @UpdateTimestamp
        @Column(name = "modified_at", nullable = false)
        open val modifiedAt: Date? = Date(),

        @ManyToOne
        @JoinColumn(name = "gym_id")
        open val gym: Gym = Gym()
)
