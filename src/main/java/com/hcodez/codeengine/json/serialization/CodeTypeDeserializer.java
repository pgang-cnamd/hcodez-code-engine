package com.hcodez.codeengine.json.serialization;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.hcodez.codeengine.model.CodeType;

import java.lang.reflect.Type;

public class CodeTypeDeserializer implements JsonDeserializer<CodeType> {
    @Override
    public CodeType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return CodeType.fromString(json.getAsJsonPrimitive().getAsString());
    }
}
