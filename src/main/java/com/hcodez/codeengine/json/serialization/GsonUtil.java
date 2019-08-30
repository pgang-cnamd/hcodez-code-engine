package com.hcodez.codeengine.json.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hcodez.codeengine.model.CodeType;

/**
 * Class that provides utility methods for JSON serialization using Gson
 */
public class GsonUtil {

    /**
     * The Gson instance
     */
    private static Gson gson;

    /**
     * Get the Gson instance
     * @return the Gson instance
     */
    public static Gson getGsonInstance() {
        if (gson ==  null) {
            synchronized (GsonUtil.class) {
                if (gson == null) {
                    gson = new GsonBuilder()
                            .registerTypeAdapter(CodeType.class, new CodeTypeSerializer())
                            .registerTypeAdapter(CodeType.class, new CodeTypeDeserializer())
                            .create();
                }
            }
        }
        return gson;
    }
}
