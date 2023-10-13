package org.acme.infra.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.acme.domain.dto.response.GeoCodeResponse
import org.acme.utils.ResponseMessages.GENERIC_MESSAGE
import org.eclipse.microprofile.rest.client.inject.RestClient

@ApplicationScoped
class GeoCodeService @Inject constructor(
    @RestClient val nominatimClient: NominatimService
) {
    fun searchAddress(address: String): Response {
        return try {
            val nominatimResponse = nominatimClient.search(address)
            Response
                .ok(GeoCodeResponse(
                    result = nominatimResponse,
                    message = GENERIC_MESSAGE,
                    status = true
                ))
                .type(MediaType.APPLICATION_JSON)
                .build()
        } catch (e: Exception) {
            Response.status(Response.Status.SERVICE_UNAVAILABLE)
                .entity("Algo deu errado ao buscar os endereços.")
                .type(MediaType.APPLICATION_JSON)
                .build()
        }
    }
}



