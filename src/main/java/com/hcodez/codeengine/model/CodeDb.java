package com.hcodez.codeengine.model;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hcodez.codeengine.json.InstantDeserializer;
import com.hcodez.codeengine.json.InstantSerializer;
import org.joda.time.Instant;

import java.io.Reader;

/**
 * Bean that represents a code exactly as it is in the database(used in the server).
 */
public class CodeDb extends Code {

    @SerializedName("id")
    private Integer id;

    @SerializedName("owner_id")
    private Integer ownerId;


    public CodeDb() {

    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }


    @Override
    public String toJson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Instant.class, new InstantSerializer());
        gsonBuilder.registerTypeAdapter(Instant.class, new InstantDeserializer());
        return gsonBuilder.create().toJson(this);
    }

    public static CodeDb fromJson(Reader input) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Instant.class, new InstantSerializer());
        gsonBuilder.registerTypeAdapter(Instant.class, new InstantDeserializer());
        return gsonBuilder.create().fromJson(input, CodeDb.class);
    }
}
