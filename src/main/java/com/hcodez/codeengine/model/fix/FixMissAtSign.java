package com.hcodez.codeengine.model.fix;

import com.hcodez.codeengine.model.CodeTypeErrorFix;

public class FixMissAtSign implements CodeTypeErrorFix {
    @Override
    public String apply(String input, int errorCharacterPointer) {
        String inputPart1 = input.substring(0, errorCharacterPointer - 1);
        String inputPart2 = input.substring(errorCharacterPointer);

        return inputPart1
                + "@"
                + inputPart2;
    }
}
