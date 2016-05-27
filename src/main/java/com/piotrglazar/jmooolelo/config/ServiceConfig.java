package com.piotrglazar.jmooolelo.config;

import java.util.OptionalInt;

public interface ServiceConfig {

    int serviceId();

    String serviceType();

    String serviceGroup();

    String environment();

    OptionalInt servicePort();
}
