package com.piotrglazar.jmooolelo.extensions;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.piotrglazar.jmooolelo.api.ServiceConfig;

import java.lang.reflect.Type;

public class ServiceConfigSerializer implements JsonSerializer<ServiceConfig> {

    @Override
    public JsonElement serialize(ServiceConfig src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        src.getConfig().forEach(object::addProperty);
        return object;
    }
}
