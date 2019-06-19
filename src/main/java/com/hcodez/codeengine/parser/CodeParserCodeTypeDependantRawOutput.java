package com.hcodez.codeengine.parser;

import java.util.ArrayList;

/**
 * Raw output parsed by a single code type using regex by the CodeParser
 * Contains a list
 */
public class CodeParserCodeTypeDependantRawOutput {

    /**
     * The list of parsed processing materials
     */
    private ArrayList<ParserProcessingMaterial> parserProcessingMaterials;

    public CodeParserCodeTypeDependantRawOutput() {
        this.parserProcessingMaterials = new ArrayList<>();
    }

    /**
     * Add a new ParserProcessingMaterial
     * @param parserProcessingMaterial the parsed material
     */
    public void addParserProcessingMaterial(ParserProcessingMaterial parserProcessingMaterial) {
        this.parserProcessingMaterials.add(parserProcessingMaterial);
    }

    /**
     * Get a parser processing material
     * @param index the index of the parser processing material
     * @return the parser processing material
     */
    public ParserProcessingMaterial getParserProcessingMaterial(int index) {
        return this.parserProcessingMaterials.get(index);
    }

    /**
     * Get the current(first) parser processing material
     * Empty-array safe method, returns null if the array is empty
     * @return the current(first) parser processing material
     */
    public ParserProcessingMaterial getCurrentParserProcessingMaterial() {
        if (this.parserProcessingMaterials.size() == 0) {
            return null;
        }
        return this.getParserProcessingMaterial(0);
    }

    /**
     * Remove the current(first) processing material from the list
     * Empty-array safe method, doesn't do anything unless the array is not empty
     */
    public void removeCurrentProcessingMaterial() {
        if (this.parserProcessingMaterials.size() > 0) {
            this.parserProcessingMaterials.remove(0);
        }
    }
}
