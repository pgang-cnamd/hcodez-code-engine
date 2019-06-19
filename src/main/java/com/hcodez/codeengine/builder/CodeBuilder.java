package com.hcodez.codeengine.builder;

import com.hcodez.codeengine.model.Code;
import org.joda.time.Instant;

import java.net.URL;

/**
 * Class that aids in the process of building a code
 */
public class CodeBuilder {

    /**
     * The code that's being built
     */
    private final Code code;


    public CodeBuilder() {
        this.code = new Code();
    }


    /**
     * Create a new CodeBuilder
     * @return the newly created CodeBuilder
     */
    public static CodeBuilder createBuilder() {
        return new CodeBuilder();
    }

    /**
     * Build the code
     * @return the built code
     */
    public Code build() {
        return this.code;
    }


    public CodeBuilder withIdentifier(String identifier) {
        this.code.setIdentifier(identifier);
        return this;
    }

    public CodeBuilder withOwner(String owner) {
        this.code.setOwner(owner);
        return this;
    }

    public CodeBuilder withPasscode(String passcode) {
        this.code.setPasscode(passcode);
        return this;
    }

    public CodeBuilder withName(String name) {
        this.code.setName(name);
        return this;
    }

    public CodeBuilder withUrl(URL url) {
        this.code.setUrl(url);
        return this;
    }

    public CodeBuilder withPublicStatus(boolean publicStatus) {
        this.code.setPublicStatus(publicStatus);
        return this;
    }

    public CodeBuilder withCreateTime(Instant createTime) {
        this.code.setCreateTime(createTime);
        return this;
    }

    public CodeBuilder withUpdateTime(Instant updateTime) {
        this.code.setUpdateTime(updateTime);
        return this;
    }
}
