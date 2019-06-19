import assertions.CodeDbAssert;
import com.hcodez.codeengine.model.CodeDb;
import org.joda.time.Instant;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class CodeDbJsonTest {

    @Test
    public void jsonToCode() throws IOException {

        CodeDb readCode = CodeDb.fromJson(TestCommon.getResourceAsString("json/code_db.json"));

        CodeDb goodCode = new CodeDb();
        goodCode.setIdentifier("aB12");
        goodCode.setOwner("cezarmathe");
        goodCode.setPasscode("d723y7x28");
        goodCode.setName("A test code");
        goodCode.setPublicStatus(true);
        goodCode.setUrl(new URL("https://api.example.com/v0/code/aB12@cezarmathe"));
        goodCode.setCreateTime(new Instant(1560354133));
        goodCode.setUpdateTime(new Instant(1560357733));
        goodCode.setId(25);
        goodCode.setOwnerId(16);

        CodeDbAssert.assertThat(readCode).isEqualTo(goodCode);
    }

    @Test
    public void codeToJson() throws IOException, JSONException {
        CodeDb codeDb = new CodeDb();
        codeDb.setIdentifier("aB12");
        codeDb.setOwner("cezarmathe");
        codeDb.setPasscode("d723y7x28");
        codeDb.setName("A test code");
        codeDb.setPublicStatus(true);
        codeDb.setUrl(new URL("https://api.example.com/v0/code/aB12@cezarmathe"));
        codeDb.setCreateTime(new Instant(1560354133));
        codeDb.setUpdateTime(new Instant(1560357733));
        codeDb.setId(25);
        codeDb.setOwnerId(16);

        JSONAssert.assertEquals(codeDb.toJson(), TestCommon.getResourceAsString("json/code_db.json"), true);
    }
}
