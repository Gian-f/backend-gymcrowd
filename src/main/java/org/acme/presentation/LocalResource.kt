package org.acme.presentation

import jakarta.inject.Inject
import jakarta.transaction.Transactional
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.acme.domain.dto.request.CreateLocalRequest
import org.acme.domain.dto.response.GenericResponse
import org.acme.infra.service.LocalService
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.tags.Tag

@Tag(name = "Local Resource")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/v1/local")
open class LocalResource @Inject constructor(
    private val service: LocalService
) {

    @Operation(summary = "Método para cadastrar local")
    @APIResponse(
        responseCode = "200", content = [Content(
            mediaType = MediaType.APPLICATION_JSON, schema = Schema(
                implementation = GenericResponse::class, type = SchemaType.ARRAY
            )
        )]
    )
    @Transactional
    @POST
    open suspend fun register(request: CreateLocalRequest): Response {
        val registry = service.register(request)
        return Response.status(Response.Status.OK).entity(registry).build()
    }

    @Operation(summary = "Método para listar todos os locais")
    @APIResponse(
        responseCode = "200", content = [Content(
            mediaType = MediaType.APPLICATION_JSON, schema = Schema(
                implementation = GenericResponse::class, type = SchemaType.ARRAY
            )
        )]
    )
    @GET
    fun listAll(): Response {
        val list = service.fetchAll()
        return Response.ok(list).build()
    }
}