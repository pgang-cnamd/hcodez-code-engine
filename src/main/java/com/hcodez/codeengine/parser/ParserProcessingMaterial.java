package com.hcodez.codeengine.parser;

import com.hcodez.codeengine.model.MutableCode;

/**
 * Processing material parsed using regex by the CodeParser
 */
public class ParserProcessingMaterial {

    /**
     * The parsed code
     */
    private MutableCode code;

    /**
     * The starting position of the parsed code
     */
    private Integer codeStartPosition;

    public ParserProcessingMaterial(MutableCode code, Integer codeStartPosition) {
        this.code = code;
        this.codeStartPosition = codeStartPosition;
    }

    public MutableCode getCode() {
        return code;
    }

    public void setCode(MutableCode code) {
        this.code = code;
    }

    public Integer getCodeStartPosition() {
        return codeStartPosition;
    }

    public void setCodeStartPosition(Integer codeStartPosition) {
        this.codeStartPosition = codeStartPosition;
    }
}
