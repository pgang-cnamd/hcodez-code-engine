package com.hcodez.codeengine.model;

import java.util.regex.Pattern;

/**
 * Code types used for parsing
 */
public enum CodeType {

    /**
     * Proper CodeTypes
     */
    PRIVATE,
    PUBLIC_NO_PASSCODE,
    PUBLIC_WITH_PASSCODE,

    /**
     * Faulty CodeTypes for detecting faulty Codes
     */
    /*private codes*/
    ERR_PRIVATE_MISS_START_BRACKET,
    ERR_PRIVATE_MISS_END_BRACKET,

    /*public codes, no passcode*/
    ERR_PUBLIC_NO_PASSCODE_MISS_START_BRACKET,
    ERR_PUBLIC_NO_PASSCODE_MISS_AT_SIGN,
    ERR_PUBLIC_NO_PASSCODE_MISS_END_BRACKET,

    /*public codes with passcode*/
    ERR_PUBLIC_WITH_PASSCODE_MISS_START_BRACKET,
    ERR_PUBLIC_WITH_PASSCODE_MISS_AT_SIGN,
    ERR_PUBLIC_WITH_PASSCODE_MISS_EXCLAM_SIGN,
    ERR_PUBLIC_WITH_PASSCODE_MISS_END_BRACKET;

    /**
     * Get the regular expression for the given code type
     * @return the regular expression
     */
    public String getRegex() {
        switch (this) {
            /*proper CodeType regex*/
            case PRIVATE:
                return "<(?<identifier>[0-9a-zA-Z]{4})>";
            case PUBLIC_NO_PASSCODE:
                return "<(?<identifier>[0-9a-zA-Z]{4})@(?<owner>[0-9a-zA-Z]{4,16})>";
            case PUBLIC_WITH_PASSCODE:
                return "<(?<identifier>[0-9a-zA-Z]{4})@(?<owner>[0-9a-zA-Z]{4,16})!(?<passcode>[0-9a-zA-Z]{4,16})>";

            /*faulty CodeType regex*/ // TODO: 2019-06-21 add regular expressions
            case ERR_PRIVATE_MISS_START_BRACKET:
                return "";
            case ERR_PRIVATE_MISS_END_BRACKET:
                return "";

            case ERR_PUBLIC_NO_PASSCODE_MISS_START_BRACKET:
                return "";
            case ERR_PUBLIC_NO_PASSCODE_MISS_AT_SIGN:
                return "";
            case ERR_PUBLIC_NO_PASSCODE_MISS_END_BRACKET:
                return "";

            case ERR_PUBLIC_WITH_PASSCODE_MISS_START_BRACKET:
                return "";
            case ERR_PUBLIC_WITH_PASSCODE_MISS_AT_SIGN:
                return "";
            case ERR_PUBLIC_WITH_PASSCODE_MISS_EXCLAM_SIGN:
                return "";
            case ERR_PUBLIC_WITH_PASSCODE_MISS_END_BRACKET:
                return "";
            default:
                throw new RuntimeException("unknown code type");
        }
    }

    /**
     * Get a compiled pattern for the code type
     * @return the pattern
     */
    public Pattern getPattern() {
        return Pattern.compile(this.getRegex());
    }
}
