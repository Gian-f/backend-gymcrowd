package org.acme.presentation

import jakarta.transaction.Transactional
import jakarta.validation.Valid
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType.APPLICATION_JSON
import jakarta.ws.rs.core.Response
import org.acme.domain.dto.request.CreateUserRequest
import org.acme.domain.dto.request.UpdateUserRequest
import org.acme.domain.dto.response.UserResponse
import org.acme.infra.service.UserService
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.tags.Tag

@Tag(name = "User Resource")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Path("/v1/users")

class UserResource(
        private val loginService: UserService
) {

    @Operation(summary = "Método para listar usuários")
    @APIResponse(responseCode = "200", content = [
        Content(mediaType = APPLICATION_JSON,
                schema = Schema(implementation = UserResponse::class,
                        type = SchemaType.ARRAY))])
    @GET
    fun list(): List<UserResponse> {
        return loginService.findAll()
    }

    @Operation(summary = "Método para buscar usuário por id")
    @APIResponse(responseCode = "200", content = [
        Content(mediaType = APPLICATION_JSON,
                schema = Schema(implementation = UserResponse::class,
                        type = SchemaType.OBJECT))])
    @GET
    @Path("{id}")
    operator fun get(@PathParam("id") id: Long): UserResponse {
        return loginService.findById(id)
    }

    @Operation(summary = "Método para adicionar usuário")
    @APIResponse(responseCode = "201", content = [
        Content(mediaType = APPLICATION_JSON,
                schema = Schema(implementation = UserResponse::class,
                        type = SchemaType.OBJECT))])
    @Transactional
    @POST
    fun create(request: @Valid CreateUserRequest): Response {
        val user: UserResponse = loginService.register(request)
        return Response.status(Response.Status.CREATED).entity(user).build()
    }

    @Operation(summary = "Método para atualizar usuário")
    @APIResponse(responseCode = "200", content = [
        Content(mediaType = APPLICATION_JSON,
                schema = Schema(implementation = UserResponse::class,
                        type = SchemaType.OBJECT))])
    @Transactional
    @PUT
    @Path("{id}")
    fun update(@PathParam("id") id: Long, request: @Valid UpdateUserRequest): Response {
        val user: UserResponse = loginService.update(id, request)
        return Response.status(Response.Status.OK).entity(user).build()
    }
}