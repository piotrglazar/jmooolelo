package com.piotrglazar.jmooolelo.providers;

import com.google.common.collect.ImmutableList;
import com.piotrglazar.jmooolelo.api.JvmSettings;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

public class JvmStartupSettingsProvider implements StartupSettingsProvider {

    private final RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();

    private final JvmSettings startupArguments = new JvmSettings(fetchStartupArguments());

    private List<String> fetchStartupArguments() {
        return ImmutableList.copyOf(runtimeMxBean.getInputArguments());
    }

    @Override
    public JvmSettings get() {
        return startupArguments;
    }
}
