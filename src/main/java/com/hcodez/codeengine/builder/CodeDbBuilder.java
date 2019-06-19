package com.hcodez.codeengine.builder;

import com.hcodez.codeengine.model.CodeDb;

/**
 * Class that aids in the process of building a code
 */
public class CodeDbBuilder extends CodeBuilder {

    /**
     * The code that's being built
     */
    private CodeDb codeDb;


    public CodeDbBuilder() {
        super();
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
        int id = this.codeDb.getId();
        int ownerId = this.codeDb.getOwnerId();

        codeDb = (CodeDb) super.build();
        codeDb.setId(id);
        codeDb.setOwnerId(ownerId);

        return codeDb;
    }


    public CodeDbBuilder withId(int id) {
        this.codeDb.setId(id);
        return this;
    }

    public CodeDbBuilder withOwnerId(int ownerId) {
        this.codeDb.setOwnerId(ownerId);
        return this;
    }
}
