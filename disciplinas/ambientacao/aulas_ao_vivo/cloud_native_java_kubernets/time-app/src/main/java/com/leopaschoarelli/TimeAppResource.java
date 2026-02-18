package com.leopaschoarelli;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/timeapp")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TimeAppResource {

    @RestClient
    TimeApiService timeApiService;

    @GET
    public String hello() {
        return "API: " + timeApiService.getTime();
    }
}
