package org.acme.presentation

import jakarta.inject.Inject
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.acme.domain.dto.request.GetAddressRequest
import org.acme.domain.dto.response.GeoCodeResponse
import org.acme.infra.service.GeoCodeService
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import java.util.concurrent.CompletableFuture

@Tag(name = "Address Resource")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/v1/address")
class GeoCodeResource @Inject constructor(
    private val geoCodeService: GeoCodeService
) {
    @POST
    fun searchAddresses(request: List<GetAddressRequest>): Response {
        val responses: MutableList<CompletableFuture<Response>> = ArrayList()

        request.forEach { req ->
            val future = CompletableFuture.supplyAsync {
                geoCodeService.searchAddress(req.address)
            }
            responses.add(future)
        }

        // Espere que todas as solicitações sejam concluídas
        CompletableFuture.allOf(*responses.toTypedArray()).join()

        val result = responses.map { it.join() }

        return if (result.all { it.status == Response.Status.OK.statusCode }) {
            val geoCodeResponses = result.map { it.readEntity(GeoCodeResponse::class.java) }
            Response
                .ok(geoCodeResponses)
                .type(MediaType.APPLICATION_JSON)
                .build()
        } else {
            Response.status(Response.Status.SERVICE_UNAVAILABLE)
                .entity("Algo deu errado ao buscar os endereços.")
                .type(MediaType.APPLICATION_JSON)
                .build()
        }
    }
}
