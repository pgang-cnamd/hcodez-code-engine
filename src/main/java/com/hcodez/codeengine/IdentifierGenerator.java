package com.hcodez.codeengine;

import com.mifmif.common.regex.Generex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdentifierGenerator {

    private static final Logger logger = LoggerFactory.getLogger(IdentifierGenerator.class);

    /**
     * The regular expression of the identifier
     */
    private static final String IDENTIFIER_REGEX = "[0-9A-Z]{4}";

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
                    logger.info("created IdentifierGenerator instance");
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
        logger.debug("requested generation of random identifier");
        return generex.random();
    }
}
