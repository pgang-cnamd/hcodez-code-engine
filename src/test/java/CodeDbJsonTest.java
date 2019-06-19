import assertions.CodeDbAssert;
import com.hcodez.codeengine.builder.CodeDbBuilder;
import com.hcodez.codeengine.model.CodeDb;
import org.joda.time.Instant;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.net.URL;

public class CodeDbJsonTest {

    @Test
    public void jsonToCode() throws IOException {

        CodeDb readCode = CodeDb.fromJson(TestCommon.getResourceAsString("json/code_db.json"));

        CodeDb goodCode = CodeDbBuilder.createBuilder()
                .withId(25)
                .withOwnerId(16)
                .withIdentifier("aB12")
                .withOwner("cezarmathe")
                .withPasscode("d723y7x28")
                .withName("A test code")
                .withPublicStatus(true)
                .withUrl(new URL("https://api.example.com/v0/code/aB12@cezarmathe"))
                .withCreateTime(new Instant(1560354133))
                .withUpdateTime(new Instant(1560357733))
                .build();

        CodeDbAssert.assertThat(readCode).isEqualTo(goodCode);
    }

    @Test
    public void codeToJson() throws IOException, JSONException {
        CodeDb codeDb = CodeDbBuilder.createBuilder()
                .withId(25)
                .withOwnerId(16)
                .withIdentifier("aB12")
                .withOwner("cezarmathe")
                .withPasscode("d723y7x28")
                .withName("A test code")
                .withPublicStatus(true)
                .withUrl(new URL("https://api.example.com/v0/code/aB12@cezarmathe"))
                .withCreateTime(new Instant(1560354133))
                .withUpdateTime(new Instant(1560357733))
                .build();

        JSONAssert.assertEquals(codeDb.toJson(), TestCommon.getResourceAsString("json/code_db.json"), true);
    }
}
