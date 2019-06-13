package com.hcodez.codeengine.patterns;

import java.util.regex.Pattern;

public final class PrivateCode {

    /**
     * Pattern for the basic private code.
     */
    private static final String     REGEX   = "<(?<identifier>[0-9a-zA-Z]{4})>";
    public  static final Pattern    PATTERN = Pattern.compile(REGEX);
    
}
