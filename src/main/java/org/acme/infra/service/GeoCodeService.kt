package org.acme.infra.service

import io.quarkus.cache.CacheResult
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.acme.domain.dto.response.GeoCodeResponse
import org.acme.domain.exception.GenericException
import org.acme.utils.ResponseMessages.GENERIC_MESSAGE
import org.eclipse.microprofile.faulttolerance.CircuitBreaker
import org.eclipse.microprofile.rest.client.inject.RestClient
import java.util.concurrent.atomic.AtomicLong

@ApplicationScoped
class GeoCodeService @Inject constructor(
    @RestClient val nominatimClient: NominatimService
) {
    private val counter = AtomicLong(0)
    @CircuitBreaker(requestVolumeThreshold = 10)
    @CacheResult(cacheName = "address-cache")
    fun searchAddress(address: String): GeoCodeResponse {
        maybeFail()
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

    private fun maybeFail() {
        val invocationNumber = counter.getAndIncrement()
        if (invocationNumber % 4 > 1) {
            throw GenericException("O Serviço de endereço está fora do ar!")
        }
    }
}



