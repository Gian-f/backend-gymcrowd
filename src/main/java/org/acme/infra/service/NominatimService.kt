package org.acme.infra.service

import jakarta.ws.rs.GET
import jakarta.ws.rs.Produces
import jakarta.ws.rs.QueryParam
import jakarta.ws.rs.core.MediaType
import org.acme.domain.model.GeoCode
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient

@RegisterRestClient(baseUri = "https://nominatim.openstreetmap.org")
interface NominatimService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun search(@QueryParam("q") query: String, @QueryParam("format") format: String = "json"): List<GeoCode>
}