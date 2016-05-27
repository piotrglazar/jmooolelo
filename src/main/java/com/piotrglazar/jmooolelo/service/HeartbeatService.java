package com.piotrglazar.jmooolelo.service;

import com.piotrglazar.jmooolelo.api.HeartbeatRequest;
import com.piotrglazar.jmooolelo.config.ClientConfig;
import com.piotrglazar.jmooolelo.config.ConcurrencyConfig;
import com.piotrglazar.jmooolelo.config.ServiceConfig;
import com.piotrglazar.jmooolelo.gateway.MoooleloGateway;
import com.piotrglazar.jmooolelo.providers.DataProvider;
import rx.Observable;

public class HeartbeatService {

    private final MoooleloGateway gateway;
    private final DataProvider dataProvider;
    private final ClientConfig clientConfig;
    private final ServiceConfig serviceConfig;
    private final ConcurrencyConfig concurrencyConfig;

    public HeartbeatService(MoooleloGateway gateway, DataProvider dataProvider, ClientConfig clientConfig,
                            ServiceConfig serviceConfig, ConcurrencyConfig concurrencyConfig) {
        this.gateway = gateway;
        this.dataProvider = dataProvider;
        this.clientConfig = clientConfig;
        this.serviceConfig = serviceConfig;
        this.concurrencyConfig = concurrencyConfig;
    }

    public void heartbeat() {
        Observable.interval(clientConfig.heartbeatInterval().getValue(), clientConfig.heartbeatInterval().getTimeUnit(),
                concurrencyConfig.intervalScheduler())
                .map(i -> request())
                .map(gateway::heartbeat)
                .observeOn(concurrencyConfig.workerScheduler())
                .subscribe();
    }

    private HeartbeatRequest request() {
        return new HeartbeatRequest(
                serviceConfig.serviceId(),
                serviceConfig.serviceType(),
                serviceConfig.serviceGroup(),
                serviceConfig.environment(),
                dataProvider.getHealthProvider().get()
        );
    }
}
