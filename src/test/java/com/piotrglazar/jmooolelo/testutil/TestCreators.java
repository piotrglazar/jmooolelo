package com.piotrglazar.jmooolelo.testutil;

import com.piotrglazar.jmooolelo.api.HealthStatus;
import com.piotrglazar.jmooolelo.api.HeartbeatRequest;
import com.piotrglazar.jmooolelo.api.RegistrationRequest;

import java.util.Optional;
import java.util.OptionalInt;

public interface TestCreators {

    default RegistrationRequest registrationRequest() {
        return new RegistrationRequest(1, "test", "test", "test", "same", "0.0.0.0",
                OptionalInt.empty(), new HealthStatus(true, Optional.empty()), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty());
    }

    default HeartbeatRequest heartbeatRequest() {
        return new HeartbeatRequest(1, "type", "group", "environment", new HealthStatus(true, Optional.empty()));
    }
}
