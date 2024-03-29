package com.hcodez.codeengine.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.hcodez.codeengine.json.serialization.GsonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
        return Code.string(this);
    }

    @Deprecated
    private static Gson getGson() {
        return GsonUtil.getGsonInstance();
    }

    public String toJson() {
        return Code.json(this);
    }

    public static MutableCode fromJson(String input) {
        return Code.mutable(Code.json(input));
    }
}
