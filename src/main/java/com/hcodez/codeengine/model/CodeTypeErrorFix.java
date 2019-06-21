package com.hcodez.codeengine.model;

/**
 * CodeTypeErrorFix is an available automatic fix for a faulty code.
 */
public interface CodeTypeErrorFix {
    /**
     * Apply the fix to a certain input code
     * @param input the input String(only the "code")
     * @return a valid code format
     */
    String apply(String input, int errorCharacterPointer);
}
