package com.piotrglazar.jmooolelo.providers;

import com.piotrglazar.jmooolelo.api.HealthStatus;

import java.util.function.Supplier;

public interface HealthProvider extends Supplier<HealthStatus> {
}
