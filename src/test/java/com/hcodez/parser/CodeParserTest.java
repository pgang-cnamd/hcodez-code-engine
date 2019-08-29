package com.hcodez.parser;

import com.hcodez.codeengine.model.Code;
import com.hcodez.codeengine.model.MutableCode;
import com.hcodez.codeengine.model.CodeType;
import com.hcodez.codeengine.parser.CodeParser;
import com.hcodez.util.CodeAssert;
import com.hcodez.util.TestCommon;
import org.junit.After;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CodeParserTest {

    @After
    public void parseStringTest() throws IOException {

        CodeParser codeParser = new CodeParser();

        List<Code> parsedList = codeParser
                .addCodeTypes(CodeType.all())
                .parseString(
                        TestCommon.getResourceAsString("/plain_text/code_parser_parse_string_test.txt")
                );

        ArrayList<Code> goodList = new ArrayList<>();

        goodList.add(
                MutableCode.builder()
                        .identifier("1111")
                        .owner("adasdasdas")
                        .passcode("823ijkdw")
                        .codeType(CodeType.PUBLIC_WITH_PASSCODE)
                        .build()
        );

        goodList.add(
                MutableCode.builder()
                        .identifier("ab12")
                        .codeType(CodeType.PRIVATE)
                        .build()
        );

        goodList.add(
                MutableCode.builder()
                        .identifier("123B")
                        .owner("cezarmathe")
                        .codeType(CodeType.PUBLIC_NO_PASSCODE)
                        .build()
        );

        goodList.add(
                MutableCode.builder()
                        .identifier("1111")
                        .owner("numelemeu")
                        .passcode("qudadjas22")
                        .codeType(CodeType.PUBLIC_WITH_PASSCODE)
                        .build()
        );

        for (int i = 0; i < parsedList.size(); i++) {
            CodeAssert.assertThat(parsedList.get(i)).isEqualTo(goodList.get(i));
        }
    }

    @After
    public void parseSingleTest() throws IOException {
        CodeParser codeParser = new CodeParser();

        List<Code> parsedList = codeParser
                .addCodeTypes(CodeType.all())
                .parseString(
                        TestCommon.getResourceAsString("/plain_text/code_parser_parse_single_test.txt")
                );

        ArrayList<Code> goodList = new ArrayList<>();

        goodList.add(
                MutableCode.builder()
                        .identifier("aB1g")
                        .codeType(CodeType.PRIVATE)
                        .build()
        );

        for (int i = 0; i < parsedList.size(); i++) {
            CodeAssert.assertThat(parsedList.get(i)).isEqualTo(goodList.get(i));
        }
    }
}
