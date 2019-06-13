import com.google.common.io.CharStreams;
import com.hcodez.codeengine.factory.CodeTypes;
import com.hcodez.codeengine.model.Code;
import com.hcodez.codeengine.factory.CodeFactory;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CodeFactoryTest {

    @Test
    public void codeFactoryAllCodeExtractionTest() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream("plain_text/code_factory_code_list.txt");

        assert inputStream != null;

        CodeFactory codeFactory = new CodeFactory();

        ArrayList<Code> list = codeFactory
                .addCodeType(CodeTypes.PRIVATE)
                .addCodeType(CodeTypes.PUBLIC_NO_PASSCODE)
                .addCodeType(CodeTypes.PUBLIC_WITH_PASSCODE)
                .getCodeListFromString(
                        CharStreams.toString(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                );

        System.out.println(list);
    }
}
