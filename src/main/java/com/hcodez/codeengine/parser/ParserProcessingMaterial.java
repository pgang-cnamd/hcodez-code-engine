package com.hcodez.codeengine.parser;

import com.hcodez.codeengine.model.Code;

/**
 * Processing material parsed using regex by the CodeParser
 */
public class ParserProcessingMaterial {

    /**
     * The parsed code
     */
    private Code code;

    /**
     * The starting position of the parsed code
     */
    private Integer codeStartPosition;

    public ParserProcessingMaterial(Code code, Integer codeStartPosition) {
        this.code = code;
        this.codeStartPosition = codeStartPosition;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public Integer getCodeStartPosition() {
        return codeStartPosition;
    }

    public void setCodeStartPosition(Integer codeStartPosition) {
        this.codeStartPosition = codeStartPosition;
    }
}
