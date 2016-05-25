package com.piotrglazar.jmooolelo;

import com.piotrglazar.jmooolelo.service.HeartbeatService;
import com.piotrglazar.jmooolelo.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class MoooleloClientImpl implements MoooleloClient {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final HeartbeatService heartbeatService = new HeartbeatService();
    private final RegistrationService registrationService = new RegistrationService();

    @Override
    public void start() {

    }
}
