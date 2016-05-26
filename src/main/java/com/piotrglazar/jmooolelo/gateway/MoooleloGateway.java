package com.piotrglazar.jmooolelo.gateway;

import com.piotrglazar.jmooolelo.api.RegistrationRequest;
import com.piotrglazar.jmooolelo.config.ServerConfig;
import com.piotrglazar.jmooolelo.extensions.PimpedGson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.lang.invoke.MethodHandles;
import java.util.concurrent.CompletableFuture;

public class MoooleloGateway {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final RestTemplate restTemplate;
    private final ServerConfig serverConfig;
    private final String registerUrl;

    public MoooleloGateway(RestTemplate restTemplate, ServerConfig serverConfig) {
        this.restTemplate = restTemplate;
        this.serverConfig = serverConfig;
        this.registerUrl = serverConfig.host() + ":" + serverConfig.port() + "/services";
    }

    public CompletableFuture<Void> register(RegistrationRequest request) {
        return CompletableFuture.supplyAsync(() ->
                restTemplate.exchange(registerUrl, HttpMethod.POST, new HttpEntity<>(PimpedGson.gson.toJson(request), httpHeaders()), String.class)
               ).thenAccept(this::successHandler)
                .exceptionally(this::exceptionHandler);
    }

    private Void successHandler(ResponseEntity<String> responseEntity) {
        HttpStatus status = responseEntity.getStatusCode();
        if (status.is2xxSuccessful()) {
            logger.info("Successfully registered service in Mooolelo ({}:{})", serverConfig.host(), serverConfig.port());
        } else if (status.is4xxClientError() || status.is5xxServerError()) {
            logger.error("Failed to register service in Mooolelo ({}:{}), got status code {} {}", serverConfig.host(),
                    serverConfig.port(), status.value(), status.getReasonPhrase());
            throw new RuntimeException(status.getReasonPhrase());
        } else {
            logger.info("Unexpected status code {} {}", status.value(), status.getReasonPhrase());
        }
        return null;
    }

    private Void exceptionHandler(Throwable t) {
        logger.error(String.format("Failed to register service in %s:%s", serverConfig.host(), serverConfig.port()), t);
        return null;
    }

    private HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
        return headers;
    }
}
