package com.piotrglazar.jmooolelo.api;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class JvmSettings {

    private final List<String> settings;

    public JvmSettings(List<String> settings) {
        this.settings = ImmutableList.copyOf(settings);
    }

    public List<String> getSettings() {
        return settings;
    }
}
