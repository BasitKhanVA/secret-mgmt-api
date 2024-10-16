package gov.va.bip.secretmgmt.controller;

import gov.va.bip.secretmgmt.model.Secret;
import gov.va.bip.secretmgmt.service.SecretService;
import gov.va.bip.secretmgmt.service.SecretServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/secrets")
@Produces(MediaType.APPLICATION_JSON)
public class SecretController {

    private final SecretService secretService;

    public SecretController() {
        this.secretService = new SecretServiceImpl();
    }

    @GET
    @Path("/{key}")
    @Operation(summary = "Get a secret by key",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Secret found",
                            content = @Content(schema = @Schema(implementation = Secret.class))),
                    @ApiResponse(responseCode = "404", description = "Secret not found")
            })
    public Response getSecret(@Parameter(description = "Secret key") @PathParam("key") String key) {
        return secretService.getSecret(key)
                .map(secret -> Response.ok(secret).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).entity("{\"error\": \"Secret not found\"}").build());
    }
}