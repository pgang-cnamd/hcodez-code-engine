import assertions.CodeAssert;
import com.hcodez.codeengine.model.Code;
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

        Code goodCode = new Code();
        goodCode.setIdentifier("aB12");
        goodCode.setOwner("cezarmathe");
        goodCode.setPasscode("d723y7x28");
        goodCode.setName("A test code");
        goodCode.setPublicStatus(true);
        goodCode.setUrl(new URL("https://api.example.com/v0/code/aB12@cezarmathe"));
        goodCode.setCreateTime(new Instant(1560354133));
        goodCode.setUpdateTime(new Instant(1560357733));

        CodeAssert.assertThat(goodCode).isEqualTo(readCode);
    }

    @Test
    public void codeToJson() throws IOException, JSONException { // FIXME: 2019-06-13 proper test that checks values
        Code code = new Code();
        code.setIdentifier("aB12");
        code.setOwner("cezarmathe");
        code.setPasscode("d723y7x28");
        code.setName("A test code");
        code.setPublicStatus(true);
        code.setUrl(new URL("https://api.example.com/v0/code/aB12@cezarmathe"));
        code.setCreateTime(new Instant(1560354133));
        code.setUpdateTime(new Instant(1560357733));

        JSONAssert.assertEquals(code.toJson(), TestCommon.getResourceAsString("json/code.json"), true);
    }
}
