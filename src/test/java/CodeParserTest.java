import assertions.CodeAssert;
import com.hcodez.codeengine.builder.CodeBuilder;
import com.hcodez.codeengine.model.Code;
import com.hcodez.codeengine.parser.CodeParser;
import com.hcodez.codeengine.model.CodeType;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class CodeParserTest {

    @Test
    public void parseStringTest() throws IOException {

        CodeParser codeParser = new CodeParser();

        ArrayList<Code> parsedList = codeParser
                .addCodeType(CodeType.PRIVATE)
                .addCodeType(CodeType.PUBLIC_NO_PASSCODE)
                .addCodeType(CodeType.PUBLIC_WITH_PASSCODE)
                .parseString(
                        TestCommon.getResourceAsString("plain_text/code_factory_code_list.txt")
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
}
