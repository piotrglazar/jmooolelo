package com.piotrglazar.jmooolelo.api;

import java.util.Optional;

public class HealthStatus {

    private final boolean isHealthy;
    private final Optional<String> reason;

    public HealthStatus(boolean isHealthy, Optional<String> reason) {
        this.isHealthy = isHealthy;
        this.reason = reason;
    }

    public Optional<String> getReason() {
        return reason;
    }

    public boolean isHealthy() {
        return isHealthy;
    }
}
