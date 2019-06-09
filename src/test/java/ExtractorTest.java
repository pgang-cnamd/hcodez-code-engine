import com.hcodez.codeengine.model.Code;
import com.hcodez.codeengine.regex.Extractor;
import org.junit.Test;

import java.util.ArrayList;

public class ExtractorTest {

    @Test
    public void extractorTest() {
        String input = "4m3kf<ab12>da<123B@cezarmathe>da878uqijkauhcz<ad>>daa,<<ada,<1111@numelemeu!qudadjas22>";

        ArrayList<Code> list = new ArrayList<>();
        list = Extractor.getCodesFromString(input);

        System.out.println(list);
    }
}
