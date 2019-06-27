package com.hcodez.util;

import com.hcodez.codeengine.model.Code;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class CodeAssert extends AbstractAssert<CodeAssert, Code> {

    public CodeAssert(Code actual) {
        super(actual, CodeAssert.class);
    }

    public static CodeAssert assertThat(Code actual) {
        return new CodeAssert(actual);
    }

    public CodeAssert isEqualTo(Code code) {

        isNotNull();

        Assertions.assertThat(actual.getCreateTime()).isEqualTo(code.getCreateTime());
        Assertions.assertThat(actual.getIdentifier()).isEqualTo(code.getIdentifier());
        Assertions.assertThat(actual.getName()).isEqualTo(code.getName());
        Assertions.assertThat(actual.getOwner()).isEqualTo(code.getOwner());
        Assertions.assertThat(actual.getPasscode()).isEqualTo(code.getPasscode());
        Assertions.assertThat(actual.getUrl()).isEqualTo(code.getUrl());
        Assertions.assertThat(actual.getUpdateTime()).isEqualTo(code.getUpdateTime());
        Assertions.assertThat(actual.getCodeType()).isEqualTo(code.getCodeType());

        return this;
    }
}
