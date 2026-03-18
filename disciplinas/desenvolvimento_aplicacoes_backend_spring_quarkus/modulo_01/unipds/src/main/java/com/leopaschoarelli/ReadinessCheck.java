package com.leopaschoarelli;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Readiness
public class ReadinessCheck implements HealthCheck {

    @RestClient
    HarryPotterService harryPotterService;

    @Override
    public HealthCheckResponse call() {

        if (harryPotterService.getSpells().contains(HarryPotterService.MSG_ERROR)) {
            return HealthCheckResponse.down("Não Estou Pronto");
        } else {
            return HealthCheckResponse.up("Estou Pronto");
        }

    }

}
