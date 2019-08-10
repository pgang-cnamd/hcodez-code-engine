package com.hcodez.codeengine.model;

import java.util.ArrayList;
import java.util.Optional;
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
    PUBLIC_WITH_PASSCODE;

    /**
     * Get the regular expression for the given code type
     *
     * @return the regular expression
     */
    public String getRegex() {
        switch (this) {
            case PRIVATE:
                return "<(?<identifier>[0-9a-zA-Z]{4})>";
            case PUBLIC_NO_PASSCODE:
                return "<(?<identifier>[0-9a-zA-Z]{4})@(?<owner>[0-9a-zA-Z]{4,16})>";
            case PUBLIC_WITH_PASSCODE:
                return "<(?<identifier>[0-9a-zA-Z]{4})@(?<owner>[0-9a-zA-Z]{4,16})!(?<passcode>[0-9a-zA-Z]{4,16})>";
            default:
                throw new RuntimeException("unknown code type");
        }
    }

    /**
     * Get a compiled pattern for the code type
     *
     * @return the pattern
     */
    public Pattern getPattern() {
        return Pattern.compile(this.getRegex());
    }

    /**
     * Get an ArrayList that contains all CodeTypes
     *
     * @return an ArrayList with every CodeType availableg
     */
    public static ArrayList<CodeType> all() {
        final ArrayList<CodeType> allCodeTypes = new ArrayList<>();

        allCodeTypes.add(CodeType.PRIVATE);
        allCodeTypes.add(CodeType.PUBLIC_NO_PASSCODE);
        allCodeTypes.add(CodeType.PUBLIC_WITH_PASSCODE);

        return allCodeTypes;
    }

    @Override
    public String toString() {
        switch (this) {
            case PRIVATE:
                return "PRIVATE";
            case PUBLIC_NO_PASSCODE:
                return "PUBLIC_NO_PASSCODE";
            case PUBLIC_WITH_PASSCODE:
                return "PUBLIC_WITH_PASSCODE";
            default:
                return "UNKNOWN";
        }
    }

    public static Optional<CodeType> fromString(String input) {
        switch (input) {
            case "PRIVATE":
                return Optional.of(CodeType.PRIVATE);
            case "PUBLIC_NO_PASSCODE":
                return Optional.of(CodeType.PUBLIC_NO_PASSCODE);
            case "PUBLIC_WITH_PASSCODE":
                return Optional.of(CodeType.PUBLIC_WITH_PASSCODE);
            default:
                return Optional.empty();
        }
    }
}
