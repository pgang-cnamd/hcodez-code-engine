package com.hcodez.codeengine.model;

import com.google.gson.reflect.TypeToken;
import com.hcodez.codeengine.json.serialization.GsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Interface describing the basic behaviour of a human code.
 */
public interface Code {
    /**
     * Get the code identifier.
     * @return the identifier
     */
    String getIdentifier();

    /**
     * Get the code owner.
     * @return the owner
     */
    String getOwner();

    /**
     * Get the code passcode.
     * @return the passcode
     */
    String getPasscode();

    /**
     * Get the code type.
     * @return the code type
     */
    CodeType getCodeType();

    static final Logger logger = LoggerFactory.getLogger(MutableCode.class);

    /**
     * Convert a code into its String representation
     * @param code the code to be converted
     * @return the code's String representation
     */
    static String string(Code code) {
        StringBuilder builder = new StringBuilder();

        builder.append("<");
        builder.append(code.getIdentifier());

        /*if the code does not have a code type, infer it's String value from it's fields*/
        if (code.getCodeType() == null) {
            try {
                if (!code.getOwner().equals("")) {
                    builder.append("@");
                    builder.append(code.getOwner());
                }
            } catch (Exception exception) {
                logger.warn(exception.getMessage());
            } finally {
                try {
                    if (!code.getPasscode().equals("")) {
                        builder.append("!");
                        builder.append(code.getPasscode());
                    }
                } catch (Exception exception) {
                    logger.warn(exception.getMessage());
                }
            }
        } else {
            /*if the code is not private, append the owner username*/
            if (code.getCodeType() != CodeType.PRIVATE) {
                builder.append("@");
                builder.append(code.getOwner());
            }
            if (code.getCodeType() == CodeType.PUBLIC_WITH_PASSCODE) {
                try {
                    if (!code.getPasscode().equals("")) {
                        builder.append("!");
                        builder.append(code.getPasscode());
                    }
                } catch (Exception exception) {
                    logger.warn(exception.getMessage());
                }
            }
        }
        builder.append(">");

        return builder.toString();
    }

    /**
     * Convert a Code into a MutableCode
     * @param code the Code
     * @return the MutableCode
     */
    static MutableCode mutable(Code code) {
        return MutableCode.builder()
                .identifier(code.getIdentifier())
                .owner(code.getOwner())
                .passcode(code.getPasscode())
                .codeType(code.getCodeType())
                .build();
    }

    /**
     * Get a Code from its JSON representation
     * @param input the JSON string
     * @return the Code
     */
    static Code json(String input) {
        Map<String, Object> tmpMap = new HashMap<>();
        Type typeToken = new TypeToken<Map<String, Object>>(){}.getType();
        tmpMap = GsonUtil.getGsonInstance().fromJson(input, typeToken);
        Map<String, Object> map = tmpMap;
        return new Code() {
            @Override
            public String getIdentifier() {
                return (String) map.get("identifier");
            }

            @Override
            public String getOwner() {
                return (String) map.get("owner");
            }

            @Override
            public String getPasscode() {
                return (String) map.get("passcode");
            }

            @Override
            public CodeType getCodeType() {
                return CodeType.fromString((String) map.get("code_type")).orElse(null);
            }
        };
    }

    /**
     * Get the JSON representation of a code
     * @param code the Code object
     * @return the Code JSON representation
     */
    static String json(Code code) {
        Map<String, String> map = new HashMap<>();
        if (code.getIdentifier() != null) {
            map.put("identifier", code.getIdentifier());
        }
        if (code.getOwner() != null) {
            map.put("owner", code.getOwner());
        }
        if (code.getPasscode() != null) {
            map.put("passcode", code.getPasscode());
        }
        if (code.getCodeType() != null) {
            map.put("code_type", code.getCodeType().toString());
        }
        return GsonUtil.getGsonInstance().toJson(map);
    }
}
