package com.hcodez.model;

import com.hcodez.codeengine.model.Code;
import com.hcodez.codeengine.model.CodeType;
import com.hcodez.codeengine.model.MutableCode;
import org.junit.Test;

public class CodeTest {

    @Test
    public void toStringTest() {
        Code code = MutableCode.builder()
                .identifier("aB12")
                .owner("testOwNeR")
                .passcode("mYPass12code")
                .codeType(CodeType.PUBLIC_WITH_PASSCODE)
                .build();

        assert code.toString().equals("<aB12@testOwNeR!mYPass12code>");
    }
}
