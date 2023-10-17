package org.acme.infra.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.acme.domain.dto.response.GeoCodeResponse
import org.acme.utils.ResponseMessages.GENERIC_MESSAGE
import org.eclipse.microprofile.rest.client.inject.RestClient

@ApplicationScoped
class GeoCodeService @Inject constructor(
    @RestClient val nominatimClient: NominatimService
) {
    fun searchAddress(address: String): GeoCodeResponse {
        return try {
            val nominatimResponse = nominatimClient.search(address)
            GeoCodeResponse(
                result = nominatimResponse,
                message = GENERIC_MESSAGE,
                status = true
            )
        } catch (e: Exception) {
            GeoCodeResponse(
                result = null,
                message = "Error: ${e.message}",
                status = false
            )
        }
    }
}



