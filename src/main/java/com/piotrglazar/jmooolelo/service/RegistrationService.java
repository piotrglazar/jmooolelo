package com.piotrglazar.jmooolelo.service;

import com.piotrglazar.jmooolelo.api.RegistrationRequest;
import com.piotrglazar.jmooolelo.config.ClientConfig;
import com.piotrglazar.jmooolelo.config.ConcurrencyConfig;
import com.piotrglazar.jmooolelo.config.ServiceConfig;
import com.piotrglazar.jmooolelo.gateway.MoooleloGateway;
import com.piotrglazar.jmooolelo.providers.ConfigurationProvider;
import com.piotrglazar.jmooolelo.providers.DataProvider;
import com.piotrglazar.jmooolelo.providers.InterfaceProvider;
import com.piotrglazar.jmooolelo.providers.StartupSettingsProvider;
import com.piotrglazar.jmooolelo.providers.VersionProvider;
import com.piotrglazar.jmooolelo.providers.WorkingDirectoryProvider;
import rx.Observable;

public class RegistrationService {

    private final MoooleloGateway gateway;
    private final DataProvider dataProvider;
    private final ClientConfig clientConfig;
    private final ServiceConfig serviceConfig;
    private final ConcurrencyConfig concurrencyConfig;

    public RegistrationService(MoooleloGateway gateway, DataProvider dataProvider, ClientConfig clientConfig,
                               ServiceConfig serviceConfig, ConcurrencyConfig concurrencyConfig) {
        this.gateway = gateway;
        this.dataProvider = dataProvider;
        this.clientConfig = clientConfig;
        this.serviceConfig = serviceConfig;
        this.concurrencyConfig = concurrencyConfig;
    }

    public void register() {
        Observable.interval(0L, clientConfig.registrationInterval().getValue(),
                clientConfig.registrationInterval().getTimeUnit(), concurrencyConfig.intervalScheduler())
                .map(i -> registrationRequest())
                .map(gateway::register)
                .observeOn(concurrencyConfig.workerScheduler())
                .subscribe();
    }

    private RegistrationRequest registrationRequest() {
        InterfaceProvider interfaceProvider = dataProvider.getInterfaceProvider();
        return new RegistrationRequest(
                serviceConfig.serviceId(),
                serviceConfig.serviceType(),
                serviceConfig.serviceGroup(),
                serviceConfig.environment(),
                interfaceProvider.hostname(),
                interfaceProvider.ip(),
                serviceConfig.servicePort(),
                dataProvider.getHealthProvider().get(),
                dataProvider.getVersionProvider().map(VersionProvider::get),
                dataProvider.getStartupSettingsProvider().map(StartupSettingsProvider::get),
                dataProvider.getConfigurationProvider().map(ConfigurationProvider::get),
                dataProvider.getWorkingDirectoryProvider().map(WorkingDirectoryProvider::get));
    }
}
