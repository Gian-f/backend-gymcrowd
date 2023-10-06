package org.acme.domain.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
open class GeoCode(
    @JsonProperty("lat")
    open val latitude: String = "",
    @JsonProperty("lon")
    open val longitude: String = "",
    @JsonProperty("display_name")
    open val displayName: String = "",
)