package com.hcodez.model;

import com.hcodez.codeengine.model.Code;
import com.hcodez.codeengine.model.CodeType;
import com.hcodez.codeengine.model.MutableCode;
import com.hcodez.util.CodeAssert;
import com.hcodez.util.TestCommon;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;

public class MutableCodeTest {

    @Test
    public void fromJsonTest() throws IOException {

        MutableCode readCode = MutableCode.fromJson(TestCommon.getResourceAsString("/json/code.json"));

        Code goodCode = MutableCode.builder()
                .identifier("aB12")
                .owner("cezarmathe")
                .passcode("d723y7x28")
                .codeType(CodeType.PUBLIC_WITH_PASSCODE)
                .build();

        CodeAssert.assertThat(goodCode).isEqualTo(readCode);
    }

    @Test
    public void toJsonTest() throws IOException, JSONException {
        MutableCode code = MutableCode.builder()
                .identifier("aB12")
                .owner("cezarmathe")
                .passcode("d723y7x28")
                .codeType(CodeType.PUBLIC_WITH_PASSCODE)
                .build();

        JSONAssert.assertEquals(code.toJson(), TestCommon.getResourceAsString("/json/code.json"), true);
    }
}
