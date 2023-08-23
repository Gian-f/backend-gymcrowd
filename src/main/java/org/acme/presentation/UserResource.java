package org.acme.presentation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.domain.dto.request.CreateUserRequest;
import org.acme.domain.dto.request.UpdateUserRequest;
import org.acme.domain.dto.response.CreateUserResponse;
import org.acme.domain.dto.response.UpdateUserResponse;
import org.acme.infra.service.UserService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
//@Authenticated
@Path("/v1/users")
public class UserResource {

    @Inject
    UserService service;

    @Operation(summary = "Método para listar usuários")
    @APIResponse(responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = CreateUserResponse.class, type = SchemaType.ARRAY)))
    @GET
    public List<CreateUserResponse> list() {
        return service.listAll();
    }

    @Operation(summary = "Método para buscar usuário por id")
    @APIResponse(responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = CreateUserResponse.class, type = SchemaType.OBJECT)))
    @GET
    @Path("{id}")
    public CreateUserResponse get(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Método para adicionar usuário")
    @APIResponse(responseCode = "201",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = CreateUserResponse.class, type = SchemaType.OBJECT)))
    @Transactional
    @POST
    public Response create(@Valid CreateUserRequest request) {
        CreateUserResponse user = service.create(request);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @Operation(summary = "Método para atualizar usuário")
    @APIResponse(responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = UpdateUserResponse.class, type = SchemaType.OBJECT)))
    @Transactional
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, @Valid UpdateUserRequest request) {
        UpdateUserResponse user = service.update(id, request);
        return Response.status(Response.Status.OK).entity(user).build();
    }
}

