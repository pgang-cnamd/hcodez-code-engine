package com.hcodez.codeengine.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Interface describing the basic behaviour of a human code.
 */
public interface Code {
    String getIdentifier();
    String getOwner();
    String getPasscode();
    CodeType getCodeType();

    static final Logger logger = LoggerFactory.getLogger(MutableCode.class);
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
}
