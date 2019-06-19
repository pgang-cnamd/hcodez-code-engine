import assertions.CodeAssert;
import com.hcodez.codeengine.model.Code;
import com.hcodez.codeengine.parser.CodeParser;
import com.hcodez.codeengine.model.CodeType;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class CodeParserTest {

    @Test
    public void codeParserParseStringTest() throws IOException {

        CodeParser codeParser = new CodeParser();

        ArrayList<Code> parsedList = codeParser
                .addCodeType(CodeType.PRIVATE)
                .addCodeType(CodeType.PUBLIC_NO_PASSCODE)
                .addCodeType(CodeType.PUBLIC_WITH_PASSCODE)
                .parseString(
                        TestCommon.getResourceAsString("plain_text/code_factory_code_list.txt")
                );

        ArrayList<Code> goodList = new ArrayList<>();

        Code code = new Code();
        code.setIdentifier("1111");
        code.setOwner("adasdasdas");
        code.setPasscode("823ijkdw");
        code.setPublicStatus(true);

        goodList.add(code);

        code = new Code();
        code.setIdentifier("ab12");

        goodList.add(code);

        code = new Code();
        code.setIdentifier("123B");
        code.setOwner("cezarmathe");
        code.setPublicStatus(true);

        goodList.add(code);

        code = new Code();
        code.setIdentifier("1111");
        code.setOwner("numelemeu");
        code.setPasscode("qudadjas22");
        code.setPublicStatus(true);

        goodList.add(code);

        for (int i = 0; i < parsedList.size(); i++) {
            CodeAssert.assertThat(parsedList.get(i)).isEqualTo(goodList.get(i));
        }
    }
}
