package com.piotrglazar.jmooolelo.config;

public interface ClientConfig {

    ServerConfig serverConfig();

    Duration registrationInterval();

    Duration heartbeatInterval();
}
