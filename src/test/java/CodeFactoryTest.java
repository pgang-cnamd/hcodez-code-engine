import com.hcodez.codeengine.factory.CodeTypes;
import com.hcodez.codeengine.model.Code;
import com.hcodez.codeengine.factory.CodeFactory;
import org.junit.Test;

import java.util.ArrayList;

public class CodeFactoryTest {

    @Test
    public void codeFactoryAllCodeExtractionTest() {
        String input = "4m3kf<ab12>da<123B@cezarmathe>da878uqijkauhcz<ad>>daa,<<ada,<1111@numelemeu!qudadjas22>";

        CodeFactory codeFactory = new CodeFactory();

        ArrayList<Code> list = codeFactory
                .addCodeType(CodeTypes.PRIVATE)
                .addCodeType(CodeTypes.PUBLIC_NO_PASSCODE)
                .addCodeType(CodeTypes.PUBLIC_WITH_PASSCODE)
                .getCodeListFromString(input);

        System.out.println(list);
    }
}
