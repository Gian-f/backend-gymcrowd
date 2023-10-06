package org.acme.domain.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection
import org.acme.domain.model.GeoCode

@JsonRootName("address")
@RegisterForReflection
data class GeoCodeResponse(
    @JsonProperty("result")
    val result: GeoCode?,

    @JsonProperty("message")
    val message: String?,

    @JsonProperty("status")
    val status: Boolean,
) {
    companion object {
        @JvmStatic
        fun build(geoCode: GeoCode, message: String?, status: Boolean): GeoCodeResponse = GeoCodeResponse(
            result = geoCode,
            message = message,
            status = status
        )
    }
}
