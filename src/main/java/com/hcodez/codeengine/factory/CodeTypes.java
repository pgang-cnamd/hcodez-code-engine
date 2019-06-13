package com.hcodez.codeengine.factory;

import com.hcodez.codeengine.patterns.PrivateCode;
import com.hcodez.codeengine.patterns.PublicCode;
import com.hcodez.codeengine.patterns.PublicCodeWithPasscode;

import java.util.regex.Pattern;

/**
 * A set of code types used for the code factory
 */
public class CodeTypes {

    /**
     * Interface that represents a single type of code, yielding a pattern for it
     */
    public interface CodeType {

        Pattern getPattern();
    }

    public static final CodeType PRIVATE = () -> PrivateCode.PATTERN;

    public static final CodeType PUBLIC_NO_PASSCODE = () -> PublicCode.PATTERN;

    public static final CodeType PUBLIC_WITH_PASSCODE = () -> PublicCodeWithPasscode.PATTERN;

}
