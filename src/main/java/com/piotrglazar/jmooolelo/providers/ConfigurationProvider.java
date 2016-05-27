package com.piotrglazar.jmooolelo.providers;

import com.piotrglazar.jmooolelo.api.ServiceConfiguration;

import java.util.function.Supplier;

public interface ConfigurationProvider extends Supplier<ServiceConfiguration> {
}
