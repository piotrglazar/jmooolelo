package com.piotrglazar.jmooolelo.service;

import com.piotrglazar.jmooolelo.config.ConcurrencyConfig;
import rx.schedulers.TestScheduler;

public class TestConcurrencyConfig implements ConcurrencyConfig {

    private final TestScheduler interval = new TestScheduler();
    private final TestScheduler worker = new TestScheduler();

    @Override
    public TestScheduler intervalScheduler() {
        return interval;
    }

    @Override
    public TestScheduler workerScheduler() {
        return worker;
    }
}
