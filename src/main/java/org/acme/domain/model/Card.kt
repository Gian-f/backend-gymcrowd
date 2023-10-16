package org.acme.domain.model

import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import org.acme.utils.ValidationMessages
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*

@Entity(name = "Cards")
@RegisterForReflection
open class Card(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long = 0,

    @field:NotBlank(message = ValidationMessages.CARD_USER_MUST_BE_NOT_BLANK)
    open var nameHolder: String = "",

    @field:NotBlank(message = ValidationMessages.CARD_NUMBER_MUST_BE_NOT_BLANK)
    @Column(unique = true)
    open var cardNumber: String = "",

    @field:NotBlank(message = ValidationMessages.CARD_FLAG_MUST_BE_NOT_BLANK)
    @Column
    open var flag: String = "",

    @Column
    open var dueDate: Date? = Date(),

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    open var createdAt: Date? = Date(),

    @UpdateTimestamp
    @Column(name = "modified_at", nullable = false)
    open var modifiedAt: Date? = Date()
)
