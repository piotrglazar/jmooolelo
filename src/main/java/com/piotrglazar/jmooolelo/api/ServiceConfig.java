package com.piotrglazar.jmooolelo.api;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class ServiceConfig {

    private final Map<String, String> config;

    public ServiceConfig(Map<String, String> config) {
        this.config = ImmutableMap.copyOf(config);
    }

    public Map<String, String> getConfig() {
        return config;
    }
}
