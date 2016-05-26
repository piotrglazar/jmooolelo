package com.piotrglazar.jmooolelo.service;

import com.piotrglazar.jmooolelo.api.RegistrationRequest;
import com.piotrglazar.jmooolelo.config.ClientConfig;
import com.piotrglazar.jmooolelo.config.ServiceConfig;
import com.piotrglazar.jmooolelo.gateway.MoooleloGateway;
import com.piotrglazar.jmooolelo.providers.DataProvider;
import com.piotrglazar.jmooolelo.providers.InterfaceProvider;
import com.piotrglazar.jmooolelo.providers.StartupSettingsProvider;
import com.piotrglazar.jmooolelo.providers.VersionProvider;
import com.piotrglazar.jmooolelo.providers.WorkingDirectoryProvider;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.Optional;

public class RegistrationService {

    private final MoooleloGateway gateway;
    private final DataProvider dataProvider;
    private final ClientConfig clientConfig;
    private final ServiceConfig serviceConfig;

    public RegistrationService(MoooleloGateway gateway, DataProvider dataProvider, ClientConfig clientConfig, ServiceConfig serviceConfig) {
        this.gateway = gateway;
        this.dataProvider = dataProvider;
        this.clientConfig = clientConfig;
        this.serviceConfig = serviceConfig;
    }

    public void register() {
        Observable.interval(0L, clientConfig.registrationInterval().getValue(), clientConfig.registrationInterval().getTimeUnit())
                .map(i -> registrationRequest())
                .map(gateway::register)
                .observeOn(Schedulers.io())
                .subscribe();
    }

    private RegistrationRequest registrationRequest() {
        InterfaceProvider interfaceProvider = dataProvider.getInterfaceProvider();
        return new RegistrationRequest(serviceConfig.serviceId(), serviceConfig.serviceType(), serviceConfig.serviceGroup(),
                serviceConfig.environment(), interfaceProvider.hostname(), interfaceProvider.ip(), serviceConfig.servicePort(),
                dataProvider.getHealthProvider().get(), dataProvider.getVersionProvider().map(VersionProvider::get),
                dataProvider.getStartupSettingsProvider().map(StartupSettingsProvider::get), Optional.empty(),
                dataProvider.getWorkingDirectoryProvider().map(WorkingDirectoryProvider::get));
    }
}
