package org.acme.presentation

import jakarta.inject.Inject
import jakarta.transaction.Transactional
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.acme.domain.dto.request.CreateCardRequest
import org.acme.domain.dto.response.CardResponse
import org.acme.infra.service.CardService
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.tags.Tag

@Tag(name = "Card Resource")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/v1/card")
class CardResource @Inject constructor(
    private val service: CardService
) {
    @Operation(summary = "Método para cadastrar cartão de crédito")
    @APIResponse(
        responseCode = "200", content = [Content(
            mediaType = MediaType.APPLICATION_JSON, schema = Schema(
                implementation = CardResponse::class, type = SchemaType.ARRAY
            )
        )]
    )
    @Transactional
    @POST
    fun registerCard(request: CreateCardRequest): Response {
        val card: CardResponse = service.createCard(request)
        return Response.status(Response.Status.OK).entity(card).build()
    }

    @Operation(summary = "Método para listar todos os cartões")
    @APIResponse(
        responseCode = "200", content = [Content(
            mediaType = MediaType.APPLICATION_JSON, schema = Schema(
                implementation = CardResponse::class, type = SchemaType.ARRAY
            )
        )]
    )
    @GET
    fun list(): Response {
        val list = service.getAllCards()
        return Response.ok(list).build()
    }

    @Transactional
    @DELETE
    @Path("/{id}")
    fun delete(@PathParam("id") id: Long): Response {
        val response = service.softDeleteCard(id)
        return if (response.status) {
            Response.ok(response).build()
        } else {
            Response.status(Response.Status.BAD_REQUEST).entity(response).build()
        }
    }
}