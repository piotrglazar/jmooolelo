package com.piotrglazar.jmooolelo.extensions;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.OptionalInt;

class OptionalIntSerializer implements JsonSerializer<OptionalInt> {

    @Override
    public JsonElement serialize(OptionalInt src, Type typeOfSrc, JsonSerializationContext context) {
        if (src.isPresent()) {
            return new JsonPrimitive(src.getAsInt());
        } else {
            return JsonNull.INSTANCE;
        }
    }
}
