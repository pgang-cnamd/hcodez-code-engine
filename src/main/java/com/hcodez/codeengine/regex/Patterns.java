package com.hcodez.codeengine.regex;

import java.util.regex.Pattern;

public class Patterns {

    /**
     * Pattern for the basic private code.
     */
    private static final String     PRIVATE_CODE = "<(?<identifier>[0-9a-zA-Z]{4})>";
    public  static final Pattern    PATTERN_PRIVATE_CODE = Pattern.compile(PRIVATE_CODE);

    /**
     * Pattern for a public code unprotected by a passcode.
     */
    private static final String     PUBLIC_CODE_NO_PASSCODE = "<(?<identifier>[0-9a-zA-Z]{4})@(?<owner>[0-9|a-z|A-Z]{4,16})>";
    public  static final Pattern    PATTERN_PUBLIC_CODE_NO_PASSCODE = Pattern.compile(PUBLIC_CODE_NO_PASSCODE);

    /**
     * Pattern for a public code protected by a passcode.
     */
    private static final String     PUBLIC_CODE_WITH_PASSCODE = "<(?<identifier>[0-9a-zA-Z]{4})@(?<owner>[0-9|a-z|A-Z]{4,16})!(?<passcode>[0-9|a-z|A-Z]{4,16})>";
    public  static final Pattern    PATTERN_PUBLIC_CODE_WITH_PASSCODE = Pattern.compile(PUBLIC_CODE_WITH_PASSCODE);

}
