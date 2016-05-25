package com.piotrglazar.jmooolelo.extensions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.piotrglazar.jmooolelo.api.JvmSettings;
import com.piotrglazar.jmooolelo.api.ServiceConfig;

import java.util.Optional;
import java.util.OptionalInt;

public class PimpedGson {

    public static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(JvmSettings.class, new JvmSettingsSerializer())
            .registerTypeAdapter(OptionalInt.class, new OptionalIntSerializer())
            .registerTypeAdapter(Optional.class, new OptionalSerializer())
            .registerTypeAdapter(ServiceConfig.class, new ServiceConfigSerializer())
            .create();
}
