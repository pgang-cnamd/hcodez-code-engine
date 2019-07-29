package com.hcodez.codeengine.json.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.joda.time.Instant;

import java.lang.reflect.Type;

/**
 * Serializer for timestamps
 */
public class InstantSerializer implements JsonSerializer<Instant> {
    @Override
    public JsonElement serialize(Instant src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getMillis());
    }
}
