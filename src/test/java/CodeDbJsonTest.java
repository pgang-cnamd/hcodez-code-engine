import com.hcodez.codeengine.model.CodeDb;
import org.joda.time.Instant;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class CodeDbJsonTest {

    @Test
    public void jsonToCode() throws IOException {

        CodeDb readCode = CodeDb.fromJson(TestCommon.getResourceAsString("json/code_db.json"));
        System.out.println("Code: " + readCode);
        System.out.println("Created at: " + readCode.getCreateTime());
        System.out.println("Edited at: " + readCode.getUpdateTime());
        System.out.println("API URL: " + readCode.getUrl().toString());
        System.out.println("Owner ID: " + readCode.getOwnerId());
        System.out.println("Code ID: " + readCode.getId());

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

        assert checkIdenticalCode(readCode, goodCode);
    }

    @Test
    public void codeToJson() throws MalformedURLException { // FIXME: 2019-06-13 proper test
        CodeDb codeDb = new CodeDb();
        codeDb.setIdentifier("aB12");
        codeDb.setOwner("cezarmathe");
        codeDb.setPasscode("d723y7x28");
        codeDb.setName("A test code");
        codeDb.setPublicStatus(true);
        codeDb.setUrl(new URL("https://api.example.com/v0/code/aB12@cezarmathe"));
        codeDb.setCreateTime(new Instant(1560354133));
        codeDb.setUpdateTime(new Instant(1560357733));

        System.out.println(codeDb.toJson());
    }

    private static boolean checkIdenticalCode(CodeDb a, CodeDb b) {
        return a.getCreateTime().toString().equals(b.getCreateTime().toString()) &&
                a.getUrl().toString().equals(b.getUrl().toString()) &&
                a.getUpdateTime().toString().equals(b.getUpdateTime().toString()) &&
                a.getIdentifier().equals(b.getIdentifier()) &&
                a.getName().equals(b.getName()) &&
                a.getOwner().equals(b.getOwner()) &&
                a.getPasscode().equals(b.getPasscode()) &&
                a.getId().equals(b.getId()) &&
                a.getOwnerId().equals(b.getOwnerId());
    }
}
