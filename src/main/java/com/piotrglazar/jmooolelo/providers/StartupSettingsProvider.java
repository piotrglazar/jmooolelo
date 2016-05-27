package com.piotrglazar.jmooolelo.providers;

import com.piotrglazar.jmooolelo.api.JvmSettings;

import java.util.function.Supplier;

public interface StartupSettingsProvider extends Supplier<JvmSettings> {
}
