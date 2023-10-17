package org.acme.presentation

import jakarta.inject.Inject
import jakarta.transaction.Transactional
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.acme.domain.dto.request.CreateGymRequest
import org.acme.domain.dto.response.GymResponse
import org.acme.infra.service.GymService
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.tags.Tag

@Tag(name = "Gym Resource")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/v1/gym")
class GymResource @Inject constructor(
    private val service: GymService
) {
    @Operation(summary = "Método para cadastrar academias")
    @APIResponse(
        responseCode = "200", content = [Content(
            mediaType = MediaType.APPLICATION_JSON, schema = Schema(
                implementation = GymResponse::class, type = SchemaType.ARRAY
            )
        )]
    )
    @Transactional
    @POST
    fun registerGym(request: CreateGymRequest): Response {
        val card: GymResponse = service.register(request)
        return Response.status(Response.Status.OK).entity(card).build()
    }

    @Operation(summary = "Método para listar todos as academias")
    @APIResponse(
        responseCode = "200", content = [Content(
            mediaType = MediaType.APPLICATION_JSON, schema = Schema(
                implementation = GymResponse::class, type = SchemaType.ARRAY
            )
        )]
    )
    @GET
    fun listAll(): Response {
        val list = service.getAllGyms()
        return Response.ok(list).build()
    }
}
