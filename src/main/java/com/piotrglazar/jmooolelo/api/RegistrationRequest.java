package com.piotrglazar.jmooolelo.api;

import java.util.Optional;
import java.util.OptionalInt;

public class RegistrationRequest implements MoooleloRequest {

    private final long id;
    private final String serviceType;
    private final String group;
    private final String environment;
    private final String hostname;
    private final String ip;
    private final OptionalInt port;
    private final HealthStatus health;
    private final Optional<String> version;
    private final Optional<JvmSettings> jvmSettings;
    private final Optional<ServiceConfiguration> config;
    private final Optional<String> workingDirectory;

    public RegistrationRequest(long id, String serviceType, String group, String environment, String hostname, String ip,
                               OptionalInt port, HealthStatus health, Optional<String> version,
                               Optional<JvmSettings> jvmSettings, Optional<ServiceConfiguration> config,
                               Optional<String> workingDirectory) {
        this.id = id;
        this.serviceType = serviceType;
        this.group = group;
        this.environment = environment;
        this.hostname = hostname;
        this.ip = ip;
        this.port = port;
        this.health = health;
        this.version = version;
        this.jvmSettings = jvmSettings;
        this.config = config;
        this.workingDirectory = workingDirectory;
    }

    public long getId() {
        return id;
    }

    public String getServiceType() {
        return serviceType;
    }

    public String getGroup() {
        return group;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getHostname() {
        return hostname;
    }

    public String getIp() {
        return ip;
    }

    public OptionalInt getPort() {
        return port;
    }

    public HealthStatus getHealth() {
        return health;
    }

    public Optional<String> getVersion() {
        return version;
    }

    public Optional<JvmSettings> getJvmSettings() {
        return jvmSettings;
    }

    public Optional<ServiceConfiguration> getConfig() {
        return config;
    }

    public Optional<String> getWorkingDirectory() {
        return workingDirectory;
    }
}
