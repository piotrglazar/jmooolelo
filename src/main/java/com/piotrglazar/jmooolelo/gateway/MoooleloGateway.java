package com.piotrglazar.jmooolelo.gateway;

import com.piotrglazar.jmooolelo.api.HeartbeatRequest;
import com.piotrglazar.jmooolelo.api.MoooleloRequest;
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
import java.util.function.Consumer;

public class MoooleloGateway {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final RestTemplate restTemplate;
    private final ServerConfig serverConfig;
    private final String registerUrl;
    private final String heartbeatUrl;

    public MoooleloGateway(RestTemplate restTemplate, ServerConfig serverConfig) {
        this.restTemplate = restTemplate;
        this.serverConfig = serverConfig;
        this.registerUrl = serverConfig.host() + ":" + serverConfig.port() + "/services";
        this.heartbeatUrl = registerUrl + "/{serviceType}/{serviceGroup}/{serviceId}";
    }

    public CompletableFuture<Void> register(RegistrationRequest request) {
        return CompletableFuture.supplyAsync(() ->
                restTemplate.exchange(registerUrl, HttpMethod.POST, httpEntity(request), String.class)
               ).thenAccept(this::registrationSuccessHandler)
                .exceptionally(this::registrationExceptionHandler);
    }

    public CompletableFuture<Void> heartbeat(HeartbeatRequest request) {
        return CompletableFuture.supplyAsync(() ->
            restTemplate.exchange(heartbeatUrl, HttpMethod.POST, httpEntity(request), String.class,
                    request.getServiceType(), request.getGroup(), request.getId())
        ).thenAccept(this::heartbeatSuccessHandler)
                .exceptionally(this::heartbeatExceptionHandler);
    }

    private HttpEntity<String> httpEntity(MoooleloRequest request) {
        return new HttpEntity<>(PimpedGson.gson.toJson(request), httpHeaders());
    }

    private Void registrationSuccessHandler(ResponseEntity<String> responseEntity) {
        return successHandler(() -> logger.info("Successfully registered service in Mooolelo ({}:{})", serverConfig.host(), serverConfig.port()),
                (status) -> logger.error("Failed to register service in Mooolelo ({}:{}), got status code {} {}", serverConfig.host(),
                        serverConfig.port(), status.value(), status.getReasonPhrase()),
                responseEntity);
    }

    private Void heartbeatSuccessHandler(ResponseEntity<String> responseEntity) {
        return successHandler(() -> logger.info("Successfully sent heartbeat to Mooolelo ({}:{})", serverConfig.host(), serverConfig.port()),
                (status) -> logger.error("Failed to send heartbeat to Mooolelo ({}:{}), got status code {} {}" ,serverConfig.host(),
                        serverConfig.port(), status.value(), status.getReasonPhrase()),
                responseEntity
        );
    }

    private Void successHandler(Runnable successAction, Consumer<HttpStatus> failureAction, ResponseEntity<String> responseEntity) {
        HttpStatus status = responseEntity.getStatusCode();
        if (status.is2xxSuccessful()) {
            successAction.run();
        } else if (status.is4xxClientError() || status.is5xxServerError()) {
            failureAction.accept(status);
            throw new RuntimeException(status.getReasonPhrase());
        } else {
            logger.info("Unexpected status code {} {}", status.value(), status.getReasonPhrase());
        }
        return null;
    }

    private Void heartbeatExceptionHandler(Throwable t) {
        return exceptionHandler(String.format("Failed to send heartbeat to %s:%s", serverConfig.host(), serverConfig.port()), t);
    }

    private Void registrationExceptionHandler(Throwable t) {
        return exceptionHandler(String.format("Failed to register service in %s:%s", serverConfig.host(), serverConfig.port()), t);
    }

    private Void exceptionHandler(String message, Throwable t) {
        logger.error(message, t);
        return null;
    }

    private HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
        return headers;
    }
}
