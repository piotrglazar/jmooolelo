package com.piotrglazar.jmooolelo.extensions;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.piotrglazar.jmooolelo.api.ServiceConfiguration;

import java.lang.reflect.Type;

class ServiceConfigSerializer implements JsonSerializer<ServiceConfiguration> {

    @Override
    public JsonElement serialize(ServiceConfiguration src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        src.getConfig().forEach(object::addProperty);
        return object;
    }
}
