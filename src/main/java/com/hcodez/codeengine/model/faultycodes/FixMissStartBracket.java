package com.hcodez.codeengine.model.faultycodes;

import com.hcodez.codeengine.model.CodeTypeErrorFix;

public class FixMissStartBracket implements CodeTypeErrorFix {
    @Override
    public String apply(String input, int errorCharacterPointer) {
        return "<" + input;
    }
}
