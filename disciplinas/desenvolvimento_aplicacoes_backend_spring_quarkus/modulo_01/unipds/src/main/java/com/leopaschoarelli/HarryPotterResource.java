package com.leopaschoarelli;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("harrypotter")
@Produces(MediaType.APPLICATION_JSON)
public class HarryPotterResource {

    @RestClient
    HarryPotterService harryPotterService;

    @GET
    @Path("spells")
    public String getSpells() {
        return harryPotterService.getSpells();
    }

}
