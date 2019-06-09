package com.hcodez.codeengine.model;

import java.util.Objects;

/**
 *  Basic bean for storing basic information related to a code.
 */
public class Code {

    private String identifier;

    private String owner;

    private String passcode;

    private String url;

    public Code(String identifier, String owner, String passcode, String url) {
        this.identifier = identifier;
        this.owner = owner;
        this.passcode = passcode;
        this.url = url;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isPublic() {
        if (this.owner == null) {
            return false;
        }
        return !this.owner.equals("");
    }

    public boolean hasPasscode() {
        if (this.passcode == null) {
            return false;
        }
        return !this.passcode.equals("");
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("<");
        builder.append(identifier);

        if (this.isPublic()) {
            builder.append("@");
            builder.append(owner);
        }

        if (this.hasPasscode()) {
            builder.append("!");
            builder.append(passcode);
        }

        builder.append(">");

        return builder.toString();
    }
}
