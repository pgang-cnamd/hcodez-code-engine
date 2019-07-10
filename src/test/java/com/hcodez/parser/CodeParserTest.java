package com.hcodez.parser;

import com.hcodez.codeengine.builder.CodeBuilder;
import com.hcodez.codeengine.model.Code;
import com.hcodez.codeengine.model.CodeType;
import com.hcodez.codeengine.parser.CodeParser;
import com.hcodez.util.CodeAssert;
import com.hcodez.util.TestCommon;
import org.junit.After;

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
                        .withCodeType(CodeType.PUBLIC_WITH_PASSCODE)
                        .build()
        );

        goodList.add(
                CodeBuilder.createBuilder()
                        .withIdentifier("ab12")
                        .withCodeType(CodeType.PRIVATE)
                        .build()
        );

        goodList.add(
                CodeBuilder.createBuilder()
                        .withIdentifier("123B")
                        .withOwner("cezarmathe")
                        .withCodeType(CodeType.PUBLIC_NO_PASSCODE)
                        .build()
        );

        goodList.add(
                CodeBuilder.createBuilder()
                        .withIdentifier("1111")
                        .withOwner("numelemeu")
                        .withPasscode("qudadjas22")
                        .withCodeType(CodeType.PUBLIC_WITH_PASSCODE)
                        .build()
        );

        for (int i = 0; i < parsedList.size(); i++) {
            CodeAssert.assertThat(parsedList.get(i)).isEqualTo(goodList.get(i));
        }
    }

    @After
    public void parseSingleTest() throws IOException {
        CodeParser codeParser = new CodeParser();

        ArrayList<Code> parsedList = codeParser
                .addCodeType(CodeType.PRIVATE)
                .addCodeType(CodeType.PUBLIC_NO_PASSCODE)
                .addCodeType(CodeType.PUBLIC_WITH_PASSCODE)
                .parseString(
                        TestCommon.getResourceAsString("/plain_text/code_parser_parse_single_test.txt")
                );

        ArrayList<Code> goodList = new ArrayList<>();

        goodList.add(
                CodeBuilder.createBuilder()
                        .withIdentifier("aB1g")
                        .withCodeType(CodeType.PRIVATE)
                        .build()
        );

        for (int i = 0; i < parsedList.size(); i++) {
            CodeAssert.assertThat(parsedList.get(i)).isEqualTo(goodList.get(i));
        }
    }
}
