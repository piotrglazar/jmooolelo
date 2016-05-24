package com.piotrglazar.jmooolelo.extensions;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Optional;

public class OptionalSerializer implements JsonSerializer<Optional<?>> {

    @Override
    public JsonElement serialize(Optional<?> src, Type typeOfSrc, JsonSerializationContext context) {
        return src.map(context::serialize).orElse(JsonNull.INSTANCE);
    }
}
