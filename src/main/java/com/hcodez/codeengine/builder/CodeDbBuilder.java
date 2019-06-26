package com.hcodez.codeengine.builder;

import com.hcodez.codeengine.model.CodeDb;
import com.hcodez.codeengine.model.CodeType;
import org.joda.time.Instant;

import java.net.URL;

/**
 * Class that aids in the process of building a code
 */
public class CodeDbBuilder{

    /**
     * The code that's being built
     */
    private final CodeDb codeDb;


    public CodeDbBuilder() {
        codeDb = new CodeDb();
    }


    /**
     * Create a new CodeDbBuilder
     * @return the newly created CodeDbBuilder
     */
    public static CodeDbBuilder createBuilder() {
        return new CodeDbBuilder();
    }

    /**
     * Build the code
     * @return the built code
     */
    public CodeDb build() {
        return codeDb;
    }


    public CodeDbBuilder withIdentifier(String identifier) {
        this.codeDb.setIdentifier(identifier);
        return this;
    }

    public CodeDbBuilder withOwner(String owner) {
        this.codeDb.setOwner(owner);
        return this;
    }

    public CodeDbBuilder withPasscode(String passcode) {
        this.codeDb.setPasscode(passcode);
        return this;
    }

    public CodeDbBuilder withName(String name) {
        this.codeDb.setName(name);
        return this;
    }

    public CodeDbBuilder withUrl(URL url) {
        this.codeDb.setUrl(url);
        return this;
    }

    public CodeDbBuilder withPublicStatus(boolean publicStatus) {
        this.codeDb.setPublicStatus(publicStatus);
        return this;
    }

    public CodeDbBuilder withCreateTime(Instant createTime) {
        this.codeDb.setCreateTime(createTime);
        return this;
    }

    public CodeDbBuilder withUpdateTime(Instant updateTime) {
        this.codeDb.setUpdateTime(updateTime);
        return this;
    }

    public CodeDbBuilder withId(int id) {
        this.codeDb.setId(id);
        return this;
    }

    public CodeDbBuilder withOwnerId(int ownerId) {
        this.codeDb.setOwnerId(ownerId);
        return this;
    }

    public CodeDbBuilder withCodeType(CodeType codeType) {
        this.codeDb.setCodeType(codeType);
        return this;
    }
}
