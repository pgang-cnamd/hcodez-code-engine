import com.hcodez.codeengine.factory.CodeFactory;
import com.hcodez.codeengine.factory.CodeTypes;
import com.hcodez.codeengine.model.Code;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class CodeFactoryTest {

    @Test
    public void codeFactoryAllCodeExtractionTest() throws IOException {

        CodeFactory codeFactory = new CodeFactory();

        ArrayList<Code> list = codeFactory
                .addCodeType(CodeTypes.PRIVATE)
                .addCodeType(CodeTypes.PUBLIC_NO_PASSCODE)
                .addCodeType(CodeTypes.PUBLIC_WITH_PASSCODE)
                .getCodeListFromString(
                        TestCommon.getResourceAsString("plain_text/code_factory_code_list.txt")
                );

        System.out.println(list);
    }
}
