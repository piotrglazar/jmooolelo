package com.piotrglazar.jmooolelo.providers;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class BasicConfigurationProvider implements ConfigurationProvider {

    private final Map<String, String> config;

    public BasicConfigurationProvider(Map<String, String> config) {
        this.config = ImmutableMap.copyOf(config);
    }

    @Override
    public Map<String, String> get() {
        return config;
    }
}
