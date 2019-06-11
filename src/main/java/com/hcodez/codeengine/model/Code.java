package com.hcodez.codeengine.model;

import java.net.URL;

/**
 * Basic bean for storing basic information related to a code.
 */
public class Code {

    /**
     * The 4 character code identifier
     */
    private String identifier;

    /**
     * The username of the owner
     */
    private String owner;

    /**
     * The passcode of this code(null => no passcode)
     */
    private String passcode = "";

    /**
     * The name of this code
     */
    private String name = "";

    /**
     * The API URL used for executing operations on this code
     */
    private URL url;

    /**
     * Flag that indicated whether the code is public or not
     */
    private Boolean publicStatus = false;

    public Code() {

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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public Boolean getPublicStatus() {
        return this.publicStatus;
    }

    public void setPublicStatus(Boolean publicStatus) {
        this.publicStatus = publicStatus;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("<");
        builder.append(identifier);

        if (this.getPublicStatus()) {
            builder.append("@");
            builder.append(owner);
        }

        if (!this.getPasscode().equals("")) {
            builder.append("!");
            builder.append(passcode);
        }

        builder.append(">");

        return builder.toString();
    }
}
