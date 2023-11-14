package org.acme.presentation

import jakarta.transaction.Transactional
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.acme.domain.dto.request.HireRequest
import org.acme.domain.dto.response.HireResponse
import org.acme.infra.service.HireService
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.tags.Tag

@Tag(name = "Hire Resource")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/v1/hire")

class HireResource(
    private val hireService: HireService
) {
    @Operation(summary = "Método para contratar serviço")
    @APIResponse(
        responseCode = "200", content = [Content(
            mediaType = MediaType.APPLICATION_JSON, schema = Schema(
                implementation = HireResponse::class, type = SchemaType.ARRAY
            )
        )]
    )
    @Transactional
    @POST
    fun register(request: HireRequest): Response {
        val hire = hireService.create(request)
        return Response.status(Response.Status.OK).entity(hire).build()
    }


    @GET
    fun list(): Response {
        val list = hireService.getAll()
        return Response.ok(list).build()
    }
}