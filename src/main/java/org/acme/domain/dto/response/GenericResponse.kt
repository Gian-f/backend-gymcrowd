package org.acme.domain.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection

@JsonRootName("generic")
@RegisterForReflection
class GenericResponse(
    @JsonProperty("result")
    val result: Any?,

    @JsonProperty("message")
    val message: String?,

    @JsonProperty("status")
    val status: Boolean,
) {
    companion object {
        @JvmStatic
        fun build(result: Any, message: String?, status: Boolean): GenericResponse = GenericResponse(
            result = result,
            message = message,
            status = status
        )
    }
}