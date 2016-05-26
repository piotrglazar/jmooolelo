package com.piotrglazar.jmooolelo.gateway;

import com.piotrglazar.jmooolelo.api.HealthStatus;
import com.piotrglazar.jmooolelo.api.RegistrationRequest;
import com.piotrglazar.jmooolelo.config.ServerConfig;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MoooleloGatewayTest {

    private MoooleloGateway gateway = new MoooleloGateway(new RestTemplate(), new ServerConfig() {
        @Override
        public String host() {
            return "http://localhost";
        }

        @Override
        public int port() {
            return 8666;
        }
    });

    @Test
    public void shouldConnectToServer() throws ExecutionException, InterruptedException {
        // given
        RegistrationRequest registrationRequest = new RegistrationRequest(1, "test", "test", "test", "same", "0.0.0.0",
                OptionalInt.empty(), new HealthStatus(true, Optional.empty()), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty());

        // when
        CompletableFuture<Void> result = gateway.register(registrationRequest);

        // then
        result.get();
    }
}