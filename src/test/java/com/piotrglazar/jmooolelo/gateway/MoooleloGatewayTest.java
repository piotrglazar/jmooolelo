package com.piotrglazar.jmooolelo.gateway;

import com.piotrglazar.jmooolelo.api.HeartbeatRequest;
import com.piotrglazar.jmooolelo.api.RegistrationRequest;
import com.piotrglazar.jmooolelo.config.ServerConfig;
import com.piotrglazar.jmooolelo.testutil.TestCreators;
import com.xebialabs.restito.server.StubServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.xebialabs.restito.builder.stub.StubHttp.whenHttp;
import static com.xebialabs.restito.semantics.Action.status;
import static com.xebialabs.restito.semantics.Action.stringContent;
import static com.xebialabs.restito.semantics.Condition.post;

public class MoooleloGatewayTest implements TestCreators {

    private StubServer server;

    private MoooleloGateway gateway;

    @Before
    public void startServerAndGateway() {
        server = new StubServer().run();
        gateway = new MoooleloGateway(new RestTemplate(), new ServerConfig() {
            @Override
            public String host() {
                return "http://localhost";
            }

            @Override
            public int port() {
                return server.getPort();
            }
        });
    }

    @After
    public void stopServer() {
        server.stop();
    }

    @Test
    public void shouldSendRegistrationRequest() throws ExecutionException, InterruptedException {
        // given
        whenHttp(server)
                .match(post("/services"))
                .then(status(HttpStatus.CREATED_201));
        RegistrationRequest registrationRequest = registrationRequest();

        // when
        CompletableFuture<Void> result = gateway.register(registrationRequest);

        // then no exception
        result.get();
    }

    @Test
    public void shouldSendHeartbeatRequest() throws ExecutionException, InterruptedException {
        // given
        whenHttp(server)
                .match(post("/services/type/group/1"))
                .then(status(HttpStatus.CREATED_201));
        HeartbeatRequest heartbeatRequest = heartbeatRequest();

        // when
        CompletableFuture<Void> result = gateway.heartbeat(heartbeatRequest);

        // then no exception
        result.get();
    }

    @Test
    public void shouldNotFailWhenServerRespondsWithHttp400AfterRegistration() throws ExecutionException, InterruptedException {
        // given
        whenHttp(server)
                .match(post("/services"))
                .then(status(HttpStatus.BAD_REQUEST_400));
        RegistrationRequest registrationRequest = registrationRequest();

        // when
        CompletableFuture<Void> result = gateway.register(registrationRequest);

        // then
        result.get();
    }

    @Test
    public void shouldNotFailWhenServerRespondsWithHttp400AfterHeartbeat() throws ExecutionException, InterruptedException {
        // given
        whenHttp(server)
                .match(post("/services/type/group/1"))
                .then(status(HttpStatus.BAD_REQUEST_400));
        HeartbeatRequest heartbeatRequest = heartbeatRequest();

        // when
        CompletableFuture<Void> result = gateway.heartbeat(heartbeatRequest);

        // then
        result.get();
    }

    @Test
    public void shouldNotFailWhenUnexpectedStatusCodeWasSent() throws ExecutionException, InterruptedException {
        // given
        whenHttp(server)
                .match(post("/services"))
                .then(status(HttpStatus.FOUND_302), stringContent("testing"));
        RegistrationRequest registrationRequest = registrationRequest();

        // when
        CompletableFuture<Void> result = gateway.register(registrationRequest);

        // then
        result.get();
    }
}
