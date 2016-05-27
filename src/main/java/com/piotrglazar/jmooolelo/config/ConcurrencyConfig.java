package com.piotrglazar.jmooolelo.config;

import rx.Scheduler;

public interface ConcurrencyConfig {

    Scheduler intervalScheduler();

    Scheduler workerScheduler();
}
