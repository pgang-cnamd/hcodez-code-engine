package com.hcodez.codeengine.patterns;

import java.util.regex.Pattern;

public final class PublicCode {

    /**
     * Pattern for a public code unprotected by a passcode.
     */
    private static final String     REGEX   = "<(?<identifier>[0-9a-zA-Z]{4})@(?<owner>[0-9a-zA-Z]{4,16})>";
    public  static final Pattern    PATTERN = Pattern.compile(REGEX);

}
