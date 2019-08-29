package com.hcodez.codeengine.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Code types used for parsing
 */
public enum CodeType {

    /**
     * Enum values
     */
    PRIVATE,
    PUBLIC_NO_PASSCODE,
    PUBLIC_WITH_PASSCODE;

    /**
     * Lists of guaranteed regular expressions for each code type.
     */
    private static List<String> PRIVATE_REGEX_LIST = null;
    private static List<String> PUBLIC_NO_PASSCODE_REGEX_LIST = null;
    private static List<String> PUBLIC_WITH_PASSCODE_REGEX_LIST = null;

    /**
     * Get the regular expression list for the given code type
     *
     * @return the regular expression list
     */
    public List<String> getRegex() {
        /*
         * If regular expression lists are not loaded, load them now
         */
        if (PRIVATE_REGEX_LIST == null
                || PUBLIC_NO_PASSCODE_REGEX_LIST == null
                || PUBLIC_WITH_PASSCODE_REGEX_LIST == null) {

            synchronized (CodeType.class) {
                if (PRIVATE_REGEX_LIST == null
                        || PUBLIC_NO_PASSCODE_REGEX_LIST == null
                        || PUBLIC_WITH_PASSCODE_REGEX_LIST == null) {

                    InputStream inputStream = CodeType.class.getResourceAsStream("code_type_regular_expressions.json");
                    Gson gson = new Gson();
                    Map<String, List<String>> data = new HashMap<>();
                    Type typeToken = new TypeToken<Map<String, List<String>>>(){}.getType();
                    data = gson.fromJson(new InputStreamReader(inputStream), typeToken);

                    PRIVATE_REGEX_LIST              = data.get(CodeType.PRIVATE.toString());
                    PUBLIC_NO_PASSCODE_REGEX_LIST   = data.get(CodeType.PUBLIC_NO_PASSCODE.toString());
                    PUBLIC_WITH_PASSCODE_REGEX_LIST = data.get(CodeType.PUBLIC_WITH_PASSCODE.toString());
                }
            }
        }
        switch (this) {
            case PRIVATE:
                return PRIVATE_REGEX_LIST;
            case PUBLIC_NO_PASSCODE:
                return PUBLIC_NO_PASSCODE_REGEX_LIST;
            case PUBLIC_WITH_PASSCODE:
                return PUBLIC_WITH_PASSCODE_REGEX_LIST;
            default:
                throw new RuntimeException("unknown code type");
        }
    }

    /**
     * Get a list of compiled patterns for the code type
     *
     * @return the pattern
     */
    public List<Pattern> getPatterns() {
        final List<Pattern> patterns = new ArrayList<>();
        for (String str : this.getRegex()) {
            patterns.add(Pattern.compile(str));
        }
        return patterns;
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
