package org.acme.infra.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.acme.domain.dto.response.GeoCodeResponse
import org.acme.utils.ResponseMessages.GENERIC_MESSAGE
import org.eclipse.microprofile.rest.client.inject.RestClient

@ApplicationScoped
class GeoCodeService {

    @Inject
    @RestClient
    lateinit var nominatimClient: NominatimService

    fun searchAddress(address: String): GeoCodeResponse {
        try {
            val nominatimResponse = nominatimClient.search(address)
            return GeoCodeResponse(
                result = nominatimResponse[0],
                message = GENERIC_MESSAGE,
                status = true
            )
        } catch (e: Exception) {
            return GeoCodeResponse(
                result = null,
                message = "Error: ${e.message}",
                status = false
            )
        }
    }
}



