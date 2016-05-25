package com.piotrglazar.jmooolelo.api;

public class HeartbeatRequest {
    private final long id;
    private final String serviceType;
    private final String group;
    private final String environment;
    private final HealthStatus health;

    public HeartbeatRequest(long id, String serviceType, String group, String environment, HealthStatus health) {
        this.id = id;
        this.serviceType = serviceType;
        this.group = group;
        this.environment = environment;
        this.health = health;
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

    public HealthStatus getHealth() {
        return health;
    }
}
