package com.piotrglazar.jmooolelo.testutil;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.piotrglazar.jmooolelo.api.HealthStatus;
import com.piotrglazar.jmooolelo.api.HeartbeatRequest;
import com.piotrglazar.jmooolelo.api.JvmSettings;
import com.piotrglazar.jmooolelo.api.RegistrationRequest;
import com.piotrglazar.jmooolelo.api.ServiceConfiguration;

import java.util.Optional;
import java.util.OptionalInt;

public interface TestCreators {

    default RegistrationRequest registrationRequest() {
        return new RegistrationRequest(1, "test", "test", "test", "same", "0.0.0.0",
                OptionalInt.empty(), new HealthStatus(true, Optional.empty()), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty());
    }

    default RegistrationRequest fullRegistrationRequest() {
        return new RegistrationRequest(1, "test", "test", "test", "same", "0.0.0.0", OptionalInt.of(10),
                new HealthStatus(true, Optional.empty()), Optional.of("0.1"),
                Optional.of(new JvmSettings(ImmutableList.of("a", "b", "c"))),
                Optional.of(new ServiceConfiguration(ImmutableMap.of("key1", "value1", "key2", "value2"))),
                Optional.of("here"));
    }

    default HeartbeatRequest heartbeatRequest() {
        return new HeartbeatRequest(1, "type", "group", "environment", new HealthStatus(true, Optional.empty()));
    }
}
