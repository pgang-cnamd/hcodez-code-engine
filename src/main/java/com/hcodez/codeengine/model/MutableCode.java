package com.hcodez.codeengine.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.hcodez.codeengine.json.serialization.CodeTypeDeserializer;
import com.hcodez.codeengine.json.serialization.CodeTypeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Basic bean for storing basic information related to a code.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MutableCode implements Code {

    private static final Logger logger = LoggerFactory.getLogger(MutableCode.class);

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
     * The CodeType of this Code
     */
    @SerializedName("code_type")
    private CodeType codeType;

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
                .registerTypeAdapter(CodeType.class, new CodeTypeSerializer())
                .registerTypeAdapter(CodeType.class, new CodeTypeDeserializer())
                .create();
    }

    public String toJson() {
        return getGson().toJson(this);
    }

    public static MutableCode fromJson(String input) {
        return getGson().fromJson(input, MutableCode.class);
    }
}
