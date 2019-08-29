package com.hcodez.util;

import com.hcodez.codeengine.model.Code;
import com.hcodez.codeengine.model.MutableCode;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class CodeAssert extends AbstractAssert<CodeAssert, Code> {

    public CodeAssert(Code actual) {
        super(actual, CodeAssert.class);
    }

    public static CodeAssert assertThat(Code actual) {
        return new CodeAssert(actual);
    }

    public CodeAssert isEqualTo(MutableCode code) {

        isNotNull();

        Assertions.assertThat(actual.getIdentifier()).isEqualTo(code.getIdentifier());
        Assertions.assertThat(actual.getOwner()).isEqualTo(code.getOwner());
        Assertions.assertThat(actual.getPasscode()).isEqualTo(code.getPasscode());
        Assertions.assertThat(actual.getCodeType()).isEqualTo(code.getCodeType());

        return this;
    }
}
