package com.piotrglazar.jmooolelo.config;

import java.util.concurrent.TimeUnit;

public class Duration {

    private final int value;
    private final TimeUnit timeUnit;

    public Duration(int value, TimeUnit timeUnit) {
        this.value = value;
        this.timeUnit = timeUnit;
    }

    public int getValue() {
        return value;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
}
