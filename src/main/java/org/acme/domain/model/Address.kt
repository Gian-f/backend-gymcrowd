package org.acme.domain.model

import io.quarkus.runtime.annotations.RegisterForReflection
import jakarta.persistence.Access
import jakarta.persistence.AccessType

@RegisterForReflection
@Access(AccessType.FIELD)
open class Address(
    open val rua: String = "",
    open val numero: String = "",
)
