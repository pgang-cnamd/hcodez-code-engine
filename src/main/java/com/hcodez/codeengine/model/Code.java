package com.hcodez.codeengine.model;

/**
 * Interface describing the basic behaviour of a human code.
 */
public interface Code {
    String getIdentifier();
    String getOwner();
    String getPasscode();
    String getCodeType();
}
