package com.hcodez.builder;

import com.hcodez.codeengine.builder.CodeBuilder;
import com.hcodez.codeengine.model.Code;
import com.hcodez.codeengine.model.CodeType;
import com.hcodez.util.CodeAssert;
import org.joda.time.Instant;
import org.junit.Before;

import java.net.MalformedURLException;
import java.net.URL;

public class CodeBuilderTest {

    @Before
    public void buildTest() throws MalformedURLException {

        Code builtCode = CodeBuilder.createBuilder()
                .withCodeType(CodeType.PUBLIC_WITH_PASSCODE)
                .withCreateTime(new Instant(1561636166))
                .withIdentifier("aB12")
                .withName("A test name")
                .withOwner("testowner")
                .withPasscode("aTesTP4ssC0de")
                .withPublicStatus(true)
                .withUpdateTime(new Instant(1561636265))
                .withUrl(new URL("https://example.com"))
                .build();

        Code goodCode = new Code();
        goodCode.setCodeType(CodeType.PUBLIC_WITH_PASSCODE);
        goodCode.setCreateTime(new Instant(1561636166));
        goodCode.setIdentifier("aB12");
        goodCode.setName("A test name");
        goodCode.setOwner("testowner");
        goodCode.setPasscode("aTesTP4ssC0de");
        goodCode.setPublicStatus(true);
        goodCode.setUpdateTime(new Instant(1561636265));
        goodCode.setUrl(new URL("https://example.com"));

        CodeAssert.assertThat(builtCode).isEqualTo(goodCode);
    }
}
