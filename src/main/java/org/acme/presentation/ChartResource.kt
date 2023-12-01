package org.acme.presentation

import jakarta.inject.Inject
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.acme.infra.service.ChartService
import org.eclipse.microprofile.openapi.annotations.tags.Tag

@Tag(name = "Chart Resource")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/v1/chart")
class ChartResource @Inject constructor(
    private val service: ChartService
) {

    @GET
    @Path("/home")
    fun chartHome(): Response {
        val list = service.homeChart()
        return Response.ok(list).build()
    }

    @GET
    @Path("/filter")
    fun chartFilter(): Response {
        val list = service.filterChart()
        return Response.ok(list).build()
    }
}