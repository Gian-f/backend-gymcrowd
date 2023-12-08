package org.acme.infra.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.acme.domain.dto.request.CreateLocalRequest
import org.acme.domain.dto.response.GenericResponse
import org.acme.domain.exception.GenericException
import org.acme.domain.model.Location
import org.acme.infra.repository.LocalRepository
import org.acme.infra.repository.LocationRepository
import org.acme.utils.ResponseMessages.GENERIC_MESSAGE

@ApplicationScoped
class LocalService @Inject constructor(
    private val repository: LocalRepository,
    private val locationRepository: LocationRepository,
    private val geoCodeService: GeoCodeService

) {
    suspend fun register(request: CreateLocalRequest): GenericResponse {
        return try {
            val registry = request.toEntity()
            repository.persist(registry)
            val nominatimResponse = geoCodeService.searchAddress(request.enderecoCompleto)
            val longitude: String
            val latitude: String
            if (nominatimResponse.result?.isNotEmpty() == true) {
                longitude = nominatimResponse.result[0].latitude
                latitude = nominatimResponse.result[0].longitude
            } else {
                throw Exception("A API de endereço não encontrou o local fornecido!")
            }

            val location = Location(
                nomeLocal = request.nomeLocal,
                enderecoCompleto = request.enderecoCompleto,
                latitude = latitude,
                longitude = longitude
            )
            locationRepository.persist(location)
            GenericResponse(
                result = location,
                message = GENERIC_MESSAGE,
                status = true
            )
        } catch (e: Exception) {
            throw GenericException("A API de endereço não encontrou o local fornecido!")
        }
    }


    fun fetchAll(): GenericResponse {
        return try {
            val response = locationRepository.fetchAll()
            GenericResponse(
                result = response,
                message = GENERIC_MESSAGE,
                status = true
            )
        } catch (e: Exception) {
            GenericResponse(
                result = emptyList<Any>(),
                message = e.message,
                status = false
            )
        }
    }
}