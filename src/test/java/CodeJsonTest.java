import assertions.CodeAssert;
import com.hcodez.codeengine.builder.CodeBuilder;
import com.hcodez.codeengine.model.Code;
import com.hcodez.codeengine.model.CodeType;
import org.joda.time.Instant;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.net.URL;

public class CodeJsonTest {

    @Test
    public void jsonToCode() throws IOException {

        Code readCode = Code.fromJson(TestCommon.getResourceAsString("json/code.json"));

        Code goodCode = CodeBuilder.createBuilder()
                .withIdentifier("aB12")
                .withOwner("cezarmathe")
                .withPasscode("d723y7x28")
                .withName("A test code")
                .withPublicStatus(true)
                .withUrl(new URL("https://api.example.com/v0/code/aB12@cezarmathe"))
                .withCreateTime(new Instant(1560354133))
                .withUpdateTime(new Instant(1560357733))
                .withCodeType(CodeType.PUBLIC_WITH_PASSCODE)
                .build();

        CodeAssert.assertThat(goodCode).isEqualTo(readCode);
    }

    @Test
    public void codeToJson() throws IOException, JSONException {
        Code code = CodeBuilder.createBuilder()
                .withIdentifier("aB12")
                .withOwner("cezarmathe")
                .withPasscode("d723y7x28")
                .withName("A test code")
                .withPublicStatus(true)
                .withUrl(new URL("https://api.example.com/v0/code/aB12@cezarmathe"))
                .withCreateTime(new Instant(1560354133))
                .withUpdateTime(new Instant(1560357733))
                .withCodeType(CodeType.PUBLIC_WITH_PASSCODE)
                .build();

        JSONAssert.assertEquals(code.toJson(), TestCommon.getResourceAsString("json/code.json"), true);
    }
}
