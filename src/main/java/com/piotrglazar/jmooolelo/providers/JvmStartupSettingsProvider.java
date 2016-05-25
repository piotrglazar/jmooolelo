package com.piotrglazar.jmooolelo.providers;

import com.google.common.collect.ImmutableList;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

public class JvmStartupSettingsProvider implements StartupSettingsProvider {

    private final RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();

    private final List<String> startupArguments = fetchStartupArguments();

    private List<String> fetchStartupArguments() {
        return ImmutableList.copyOf(runtimeMxBean.getInputArguments());
    }

    @Override
    public List<String> get() {
        return startupArguments;
    }
}
