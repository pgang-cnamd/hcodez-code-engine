package com.hcodez.codeengine.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
}
