package org.acme.presentation

import jakarta.inject.Inject
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.acme.domain.dto.request.GetAddressRequest
import org.acme.infra.service.GeoCodeService
import org.eclipse.microprofile.openapi.annotations.tags.Tag

@Tag(name = "Address Resource")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/v1/address")
class GeoCodeResource {

    @Inject
    lateinit var geoCodeService: GeoCodeService

    @POST
    fun searchAddress(request: GetAddressRequest): Response {
        val nominatimResponse = geoCodeService.searchAddress(request.address)
        return Response
            .ok(nominatimResponse)
            .type(MediaType.APPLICATION_JSON)
            .build()
    }
}