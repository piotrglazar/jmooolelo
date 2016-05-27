package com.piotrglazar.jmooolelo.providers;

import com.google.common.collect.ImmutableMap;
import com.piotrglazar.jmooolelo.api.ServiceConfiguration;

import java.util.Map;

public class BasicConfigurationProvider implements ConfigurationProvider {

    private final ServiceConfiguration config;

    public BasicConfigurationProvider(Map<String, String> config) {
        this(new ServiceConfiguration(ImmutableMap.copyOf(config)));
    }

    public BasicConfigurationProvider(ServiceConfiguration config) {
        this.config = config;
    }

    @Override
    public ServiceConfiguration get() {
        return config;
    }
}
