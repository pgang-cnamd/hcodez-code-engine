package com.hcodez.codeengine;

import com.mifmif.common.regex.Generex;

public class IdentifierGenerator {

    /**
     * The regular expression of the identifier
     */
    private static final String IDENTIFIER_REGEX = "[0-9a-zA-Z]{4}";

    /**
     * The generator instance
     */
    private static IdentifierGenerator sIdentifierGenerator;

    /**
     * The Generex instance
     */
    private final Generex generex;


    private IdentifierGenerator() {
        this.generex = new Generex(IDENTIFIER_REGEX);
    }


    /**
     * Get the generator instance
     * @return the instance
     */
    public static IdentifierGenerator getInstance() {
        if (sIdentifierGenerator == null) {
            synchronized (IdentifierGenerator.class) {
                if (sIdentifierGenerator == null) {
                    sIdentifierGenerator = new IdentifierGenerator();
                }
            }
        }
        return sIdentifierGenerator;
    }

    /**
     * Generate a random identifier
     * @return the randomly generated identifier
     */
    public String generateIdentifier() {
        return generex.random();
    }
}
