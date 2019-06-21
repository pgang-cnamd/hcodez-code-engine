package com.hcodez.codeengine.model;

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
}
