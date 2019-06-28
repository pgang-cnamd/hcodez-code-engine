package com.hcodez.parser;

import com.hcodez.codeengine.builder.CodeBuilder;
import com.hcodez.codeengine.model.Code;
import com.hcodez.codeengine.model.CodeType;
import com.hcodez.codeengine.parser.CodeParser;
import com.hcodez.util.CodeAssert;
import com.hcodez.util.TestCommon;
import com.hcodez.util.Xml;
import org.junit.After;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class CodeParserTest {

    @After
    public void parseStringTest() throws IOException {

        CodeParser codeParser = new CodeParser();

        ArrayList<Code> parsedList = codeParser
                .addCodeType(CodeType.PRIVATE)
                .addCodeType(CodeType.PUBLIC_NO_PASSCODE)
                .addCodeType(CodeType.PUBLIC_WITH_PASSCODE)
                .parseString(
                        TestCommon.getResourceAsString("/plain_text/code_factory_code_list.txt")
                );

        ArrayList<Code> goodList = new ArrayList<>();

        goodList.add(
                CodeBuilder.createBuilder()
                        .withIdentifier("1111")
                        .withOwner("adasdasdas")
                        .withPasscode("823ijkdw")
                        .withPublicStatus(true)
                        .build()
        );

        goodList.add(
                CodeBuilder.createBuilder()
                        .withIdentifier("ab12")
                        .build()
        );

        goodList.add(
                CodeBuilder.createBuilder()
                        .withIdentifier("123B")
                        .withOwner("cezarmathe")
                        .withPublicStatus(true)
                        .build()
        );

        goodList.add(
                CodeBuilder.createBuilder()
                        .withIdentifier("1111")
                        .withOwner("numelemeu")
                        .withPasscode("qudadjas22")
                        .withPublicStatus(true)
                        .build()
        );

        for (int i = 0; i < parsedList.size(); i++) {
            CodeAssert.assertThat(parsedList.get(i)).isEqualTo(goodList.get(i));
        }
    }

    @Test
    public void parseEasyPrivateCodes() throws IOException, ParserConfigurationException, SAXException {
        CodeParser parser = new CodeParser();

        ArrayList<Code> parsedList = parser
                .addCodeType(CodeType.PRIVATE)
                .parseString(TestCommon.getResourceAsString("/plain_text/codes_private.txt"));

        ArrayList<Code> goodList = Xml.loadCodesFromXml(CodeType.PRIVATE);

        assert parsedList.size() == goodList.size();

        for (int i = 0; i < parsedList.size(); i++) {
            CodeAssert.assertThat(parsedList.get(i)).isEqualTo(goodList.get(i));
        }
    }
}
