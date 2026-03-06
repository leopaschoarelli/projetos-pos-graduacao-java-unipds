package com.leopaschoarelli;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "https://hp-api.onrender.com/api/")
public interface HarryPotterService {

    public static final String MSG_ERROR = "Fallback ";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path( "spells")
    @Timeout(value = 3000L)
    @Fallback(fallbackMethod = "getSpellsFallback")
    @CircuitBreaker(requestVolumeThreshold = 5, failureRatio = 0.5, delay = 3000L, successThreshold = 2)
    public String getSpells();

    default String getSpellsFallback() {
        return MSG_ERROR;
    }

}
