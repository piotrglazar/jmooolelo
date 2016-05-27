package com.piotrglazar.jmooolelo.extensions;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.piotrglazar.jmooolelo.api.JvmSettings;

import java.lang.reflect.Type;

class JvmSettingsSerializer implements JsonSerializer<JvmSettings> {

    @Override
    public JsonElement serialize(JvmSettings src, Type typeOfSrc, JsonSerializationContext context) {
        JsonArray array = new JsonArray();
        src.getSettings().stream().forEach(array::add);
        return array;
    }
}
