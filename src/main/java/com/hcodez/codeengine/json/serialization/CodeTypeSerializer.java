package com.hcodez.codeengine.json.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.hcodez.codeengine.model.CodeType;

import java.lang.reflect.Type;

public class CodeTypeSerializer implements JsonSerializer<CodeType> {
    @Override
    public JsonElement serialize(CodeType src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toString());
    }
}
