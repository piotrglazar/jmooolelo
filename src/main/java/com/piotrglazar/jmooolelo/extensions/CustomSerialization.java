package com.piotrglazar.jmooolelo.extensions;

import com.google.gson.JsonSerializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a serializator for a non-serializable class
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CustomSerialization {
    Class<? extends JsonSerializer> value();
}
