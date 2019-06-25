package com.hcodez.codeengine.model;

import com.hcodez.codeengine.model.fix.*;

/**
 * CodeTypeError is a container designed to provide useful information regarding faulty CodeTypes
 */
public class CodeTypeError {

    /**
     * The error message displayed to the user
     */
    private final String errorMessage;

    /**
     * The character(starting from 0) where the error is located(or starts)
     *
     * -1 indicated an unknown character pointer
     */
    private int errorCharacterPointer;

    /**
     * A possible fix for this error
     *
     * A automatic fix is not always guaranteed.
     */
    private CodeTypeErrorFix fix;


    /**
     * Create a new CodeTypeError, with an unknown errorCharacterPointer and no automatic fix
     * @param errorMessage the error message that should be displayed to the user
     */
    public CodeTypeError(String errorMessage) {
        this.errorMessage = errorMessage;
        this.errorCharacterPointer = -1;
        this.fix = null;
    }

    /**
     * Create a new CodeTypeError, with a known errorCharacterPointer, but no automatic fix
     * @param errorMessage the error message that should be displayed to the user
     * @param errorCharacterPointer the index of the character where the error starts
     */
    public CodeTypeError(String errorMessage, int errorCharacterPointer) {
        this.errorMessage = errorMessage;
        this.errorCharacterPointer = errorCharacterPointer;
        this.fix = null;
    }

    /**
     * Create a new CodeTypeError, with a known errorCharacterPointer and an automatic fix available
     * @param errorMessage the error message that should be displayed to the user
     * @param errorCharacterPointer the index of the character where the error starts
     * @param fix the available automatic fix
     */
    public CodeTypeError(String errorMessage, int errorCharacterPointer, CodeTypeErrorFix fix) {
        this.errorMessage = errorMessage;
        this.errorCharacterPointer = errorCharacterPointer;
        this.fix = fix;
    }


    /**
     * Get a CodeTypeError for an unknown CodeType error
     * @return the CodeTypeError
     */
    public static CodeTypeError unknownError() {
        return new CodeTypeError(
                "unknown error",
                -1,
                new FixNotAvailable()
        );
    }

    /**
     * Get a CodeTypeError for a known CodeTypeError that does not have a fix
     * @return the CodeTypeError
     */
    public static CodeTypeError knownErrorNoFix(String errorMessage) {
        return new CodeTypeError(
                errorMessage + "; no fix available",
                -1,
                new FixNotAvailable()
        );
    }

    /**
     * Get a CodeTypeError for a good code that doesn't have any error(s)
     * @return the CodeTypeError
     */
    public static CodeTypeError noError() {
        return new CodeTypeError(
                "no error",
                -1,
                new FixNotNeeded()
        );
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorCharacterPointer() {
        return errorCharacterPointer;
    }

    public void setErrorCharacterPointer(int errorCharacterPointer) {
        this.errorCharacterPointer = errorCharacterPointer;
    }

    public CodeTypeErrorFix getFix() {
        return fix;
    }

    public void setFix(CodeTypeErrorFix fix) {
        this.fix = fix;
    }


    /**
     * Get a CodeTypeError for a specific CodeType
     * @param codeType the code type
     * @return a CodeTypeError for a specific CodeType
     */
    public static CodeTypeError getFromCodeType(CodeType codeType) {
        if (!codeType.isError()) {
            return CodeTypeError.noError();
        }

        switch (codeType) {

            case ERR_PRIVATE_MISS_START_BRACKET:
            case ERR_PUBLIC_NO_PASSCODE_MISS_START_BRACKET:
            case ERR_PUBLIC_WITH_PASSCODE_MISS_START_BRACKET:
                return new CodeTypeError(
                        "missing the start bracket(\"<\")",
                        0,
                        new FixMissStartBracket()
                );

            case ERR_PRIVATE_MISS_END_BRACKET:
            case ERR_PUBLIC_NO_PASSCODE_MISS_END_BRACKET:
            case ERR_PUBLIC_WITH_PASSCODE_MISS_END_BRACKET:
                return new CodeTypeError(
                        "missing the end bracket(\">\")",
                        -1,
                        new FixMissEndBracket()
                );

            case ERR_PUBLIC_NO_PASSCODE_MISS_AT_SIGN:
            case ERR_PUBLIC_WITH_PASSCODE_MISS_AT_SIGN:
                return new CodeTypeError(
                        "missing the username decorator(\"@\")",
                        5,
                        new FixMissAtSign()
                );

            case ERR_PUBLIC_WITH_PASSCODE_MISS_EXCLAMATION_SIGN:R:
                return CodeTypeError.knownErrorNoFix("username exceeds length and passcode decorator not found(\"!\")");

            default:
                return CodeTypeError.unknownError();
        }
    }

    // TODO: 2019-06-25 add error metadata(position in string etc)
}
