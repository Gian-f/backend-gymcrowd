package org.acme.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import jdk.jfr.BooleanFlag
import org.acme.utils.ValidationMessages.EMAIL_MUST_BE_NOT_BLANK
import org.acme.utils.ValidationMessages.PASSWORD_MUST_BE_NOT_BLANK
import org.acme.utils.ValidationMessages.USERNAME_MUST_BE_NOT_BLANK
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*

@Entity(name = "Users")
@RegisterForReflection
open class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        open var id: Long = 0,

        @field:NotBlank(message = USERNAME_MUST_BE_NOT_BLANK)
        open var username: String = "",

        @field:Email
        @field:NotBlank(message = EMAIL_MUST_BE_NOT_BLANK)
        @Column(unique = true)
        open var email: String = "",

        @field:NotBlank(message = PASSWORD_MUST_BE_NOT_BLANK)
        @JsonIgnore
        open var password: String = "",

        @field:Size(min = 0, max = 255)
        @Column(unique = true)
        open var cpf: String = "",

        @field:BooleanFlag
        open var status: Boolean = true,

        @CreationTimestamp
        @Column(name = "created_at", nullable = false, updatable = false)
        open var createdAt: Date? = Date(),

        @UpdateTimestamp
        @Column(name = "modified_at", nullable = false)
        open var modifiedAt: Date? = Date()
)
