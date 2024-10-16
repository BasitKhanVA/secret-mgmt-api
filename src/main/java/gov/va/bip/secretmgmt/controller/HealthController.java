package gov.va.bip.secretmgmt.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/health")
public class HealthController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHealth() {
        return "{\"status\": \"UP\"}";
    }
}