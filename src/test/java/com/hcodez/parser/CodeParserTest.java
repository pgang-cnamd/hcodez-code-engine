package com.hcodez.parser;

import com.hcodez.codeengine.model.Code;
import com.hcodez.codeengine.model.CodeType;
import com.hcodez.codeengine.model.MutableCode;
import com.hcodez.codeengine.parser.CodeParser;
import com.hcodez.util.CodeAssert;
import com.hcodez.util.TestCommon;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

public class CodeParserTest {

    @Test
    public void parseStringTest() throws IOException {

        CodeParser codeParser = new CodeParser();

        Set<Code> parsedSet = codeParser
                .addCodeTypes(CodeType.all())
                .parseString(
                        TestCommon.getResourceAsString("/plain_text/code_parser_parse_string_test.txt")
                );

        Set<Code> goodSet = new HashSet<>();

        goodSet.add(
                MutableCode.builder()
                        .identifier("1111")
                        .owner("adasdasdas")
                        .passcode("823ijkdw")
                        .codeType(CodeType.PUBLIC_WITH_PASSCODE)
                        .build()
        );

        goodSet.add(
                MutableCode.builder()
                        .identifier("ab12")
                        .codeType(CodeType.PRIVATE)
                        .build()
        );

        goodSet.add(
                MutableCode.builder()
                        .identifier("123B")
                        .owner("cezarmathe")
                        .codeType(CodeType.PUBLIC_NO_PASSCODE)
                        .build()
        );

        goodSet.add(
                MutableCode.builder()
                        .identifier("1111")
                        .owner("numelemeu")
                        .passcode("qudadjas22")
                        .codeType(CodeType.PUBLIC_WITH_PASSCODE)
                        .build()
        );

        if (parsedSet.size() != goodSet.size()) {
            throw new java.lang.IllegalStateException("test lists not equal in size; parsed list has "
                    + parsedSet.size()
                    + "; good list has "
                    + goodSet.size());
        }

        Iterator<Code> goodSetIterator = goodSet.iterator();
        Iterator<Code> parsedSetIterator = parsedSet.iterator();
        while (goodSetIterator.hasNext()) {
            CodeAssert.assertThat(goodSetIterator.next()).isEqualTo(parsedSetIterator.next());
        }
    }

    @Test
    public void parseSingleTest() throws IOException {
        CodeParser codeParser = new CodeParser();

        Code parsedCode = codeParser
                .addCodeTypes(CodeType.all())
                .parseSingle(
                        TestCommon.getResourceAsString("/plain_text/code_parser_parse_single_test.txt")
                );

        Code goodCode = MutableCode.builder()
                .identifier("aB1g")
                .codeType(CodeType.PRIVATE)
                .build();

        CodeAssert.assertThat(parsedCode).isEqualTo(goodCode);
    }
}
