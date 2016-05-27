package com.piotrglazar.jmooolelo.api;

public interface MoooleloRequest {

    long getId();
    String getServiceType();
    String getGroup();
    String getEnvironment();
}
