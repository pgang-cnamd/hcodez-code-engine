import com.hcodez.codeengine.parser.CodeParser;
import com.hcodez.codeengine.parser.CodeType;
import com.hcodez.codeengine.model.Code;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class CodeParserTest {

    @Test
    public void codeFactoryAllCodeExtractionTest() throws IOException {

        CodeParser codeParser = new CodeParser();

        ArrayList<Code> list = codeParser
                .addCodeType(CodeType.PRIVATE)
                .addCodeType(CodeType.PUBLIC_NO_PASSCODE)
                .addCodeType(CodeType.PUBLIC_WITH_PASSCODE)
                .parseString(
                        TestCommon.getResourceAsString("plain_text/code_factory_code_list.txt")
                );

        System.out.println(list);
    }
}
