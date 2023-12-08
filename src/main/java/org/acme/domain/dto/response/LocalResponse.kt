package org.acme.domain.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection
import org.acme.domain.model.Location

@JsonRootName("local")
@RegisterForReflection
open class LocalResponse(
    @JsonProperty("result")
    val result: Any?,

    @JsonProperty("local")
    val local: Location,

    @JsonProperty("message")
    val message: String?,

    @JsonProperty("status")
    val status: Boolean,
) {
    companion object {
        @JvmStatic
        fun build(result: Any, message: String?, local: Location, status: Boolean): LocalResponse = LocalResponse(
            result = result,
            local = local,
            message = message,
            status = status
        )
    }
}