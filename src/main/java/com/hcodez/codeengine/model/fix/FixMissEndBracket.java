package com.hcodez.codeengine.model.fix;

import com.hcodez.codeengine.model.CodeTypeErrorFix;

public class FixMissEndBracket implements CodeTypeErrorFix {
    @Override
    public String apply(String input, int errorCharacterPointer) {
        return input + ">";
    }
}
