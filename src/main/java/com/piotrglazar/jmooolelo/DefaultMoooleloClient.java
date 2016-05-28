package com.piotrglazar.jmooolelo;

import com.piotrglazar.jmooolelo.config.ClientConfig;
import com.piotrglazar.jmooolelo.config.ConcurrencyConfig;
import com.piotrglazar.jmooolelo.config.ServiceConfig;
import com.piotrglazar.jmooolelo.gateway.MoooleloGateway;
import com.piotrglazar.jmooolelo.providers.DataProvider;
import com.piotrglazar.jmooolelo.service.HeartbeatService;
import com.piotrglazar.jmooolelo.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import java.lang.invoke.MethodHandles;

public class DefaultMoooleloClient implements MoooleloClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final MoooleloGateway gateway;
    private final HeartbeatService heartbeatService;
    private final RegistrationService registrationService;

    public DefaultMoooleloClient(ClientConfig clientConfig, ServiceConfig serviceConfig, DataProvider dataProvider) {
        this.gateway = new MoooleloGateway(new RestTemplate(), clientConfig.serverConfig());
        this.heartbeatService = new HeartbeatService(gateway, dataProvider, clientConfig, serviceConfig, defaultConcurrencyConfig());
        this.registrationService = new RegistrationService(gateway, dataProvider, clientConfig, serviceConfig, defaultConcurrencyConfig());
    }

    @Override
    public void start() {
        LOGGER.info("Starting mooolelo client");
        registrationService.register();
        heartbeatService.heartbeat();
    }

    private ConcurrencyConfig defaultConcurrencyConfig() {
        return new ConcurrencyConfig() {
            @Override
            public Scheduler intervalScheduler() {
                return Schedulers.computation();
            }

            @Override
            public Scheduler workerScheduler() {
                return Schedulers.io();
            }
        };
    }
}
