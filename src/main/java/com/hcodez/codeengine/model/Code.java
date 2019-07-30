package com.hcodez.codeengine.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.hcodez.codeengine.json.serialization.CodeTypeDeserializer;
import com.hcodez.codeengine.json.serialization.CodeTypeSerializer;
import com.hcodez.codeengine.json.serialization.InstantDeserializer;
import com.hcodez.codeengine.json.serialization.InstantSerializer;
import org.joda.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * Basic bean for storing basic information related to a code.
 */
public class Code {

    private static final Logger logger = LoggerFactory.getLogger(Code.class);

    /**
     * The 4 character code identifier
     */
    @SerializedName("identifier")
    private String identifier;

    /**
     * The username of the owner
     */
    @SerializedName("owner")
    private String owner;

    /**
     * The passcode of this code(empty => no passcode)
     */
    @SerializedName("passcode")
    private String passcode;

    /**
     * The name of this code
     */
    @SerializedName("name")
    private String name;

    /**
     * The API URL used for executing operations on this code
     */
    @SerializedName("url")
    private URL url;

    /**
     * The timestamp of the creation of the code
     */
    @SerializedName("create_time")
    private Instant createTime;

    /**
     * The timestamp of the last edit of the code
     */
    @SerializedName("update_time")
    private Instant updateTime;

    /**
     * The CodeType of this Code
     */
    @SerializedName("code_type")
    private CodeType codeType;


    public Code() {

    }


    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public CodeType getCodeType() {
        return codeType;
    }

    public void setCodeType(CodeType codeType) {
        this.codeType = codeType;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("<");
        builder.append(identifier);

        /*if the code does not have a code type, infer it's String value from it's fields*/
        if (this.codeType == null) {
            try {
                if (!this.getOwner().equals("")) {
                    builder.append("@");
                    builder.append(owner);
                }
            } catch (Exception exception) {
                logger.warn(exception.getMessage());
            } finally {
                try {
                    if (!this.getPasscode().equals("")) {
                        builder.append("!");
                        builder.append(passcode);
                    }
                } catch (Exception exception) {
                    logger.warn(exception.getMessage());
                }
            }
        } else {
            /*if the code is not private, append the owner username*/
            if (this.codeType != CodeType.PRIVATE) {
                builder.append("@");
                builder.append(owner);
            }
            if (this.codeType == CodeType.PUBLIC_WITH_PASSCODE) {
                try {
                    if (!this.getPasscode().equals("")) {
                        builder.append("!");
                        builder.append(passcode);
                    }
                } catch (Exception exception) {
                    logger.warn(exception.getMessage());
                }
            }
        }

        builder.append(">");

        return builder.toString();
    }

    private static Gson getGson() {
        return new GsonBuilder()
                .registerTypeAdapter(Instant.class, new InstantSerializer())
                .registerTypeAdapter(Instant.class, new InstantDeserializer())
                .registerTypeAdapter(CodeType.class, new CodeTypeSerializer())
                .registerTypeAdapter(CodeType.class, new CodeTypeDeserializer())
                .create();
    }

    public String toJson() {
        return getGson().toJson(this);
    }

    public static Code fromJson(String input) {
        return getGson().fromJson(input, Code.class);
    }
}
