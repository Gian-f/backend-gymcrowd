package org.acme.presentation

import jakarta.inject.Inject
import jakarta.transaction.Transactional
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.acme.domain.dto.request.SyncGymRequest
import org.acme.domain.dto.response.GenericResponse
import org.acme.infra.service.SyncService
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.tags.Tag

@Tag(name = "Sync Gym Resource")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/v1/sync/gym")
class SyncResource @Inject constructor(
    private val service: SyncService
) {

    @Operation(summary = "Método para sincronizar academia")
    @APIResponse(
        responseCode = "200", content = [Content(
            mediaType = MediaType.APPLICATION_JSON, schema = Schema(
                implementation = GenericResponse::class, type = SchemaType.ARRAY
            )
        )]
    )
    @Transactional
    @POST
    fun register(request: SyncGymRequest): Response {
        val registry = service.register(request)
        return Response.status(Response.Status.OK).entity(registry).build()
    }
}