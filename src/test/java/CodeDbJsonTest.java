import com.hcodez.codeengine.model.CodeDb;
import org.joda.time.Instant;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CodeDbJsonTest {

    @Test
    public void jsonToCode() throws MalformedURLException {

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream("json/code_db.json");

        assert inputStream != null;

        CodeDb readCode = CodeDb.fromJson(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        System.out.println("Code: " + readCode);
        System.out.println("Created at: " + readCode.getCreateTime());
        System.out.println("Edited at: " + readCode.getEditTime());
        System.out.println("API URL: " + readCode.getUrl().toString());
        System.out.println("Owner ID: " + readCode.getOwnerId());
        System.out.println("Code ID: " + readCode.getId());

        CodeDb goodCode = readCode;
        goodCode.setIdentifier("aB12");
        goodCode.setOwner("cezarmathe");
        goodCode.setPasscode("d723y7x28");
        goodCode.setName("A test code");
        goodCode.setPublicStatus(true);
        goodCode.setUrl(new URL("https://api.example.com/v0/code/aB12@cezarmathe"));
        goodCode.setCreateTime(new Instant(1560354133));
        goodCode.setEditTime(new Instant(1560357733));

        assert readCode.equals(goodCode);
    }

    @Test
    public void codeToJson() throws MalformedURLException {
        CodeDb codeDb = new CodeDb();
        codeDb.setIdentifier("aB12");
        codeDb.setOwner("cezarmathe");
        codeDb.setPasscode("d723y7x28");
        codeDb.setName("A test code");
        codeDb.setPublicStatus(true);
        codeDb.setUrl(new URL("https://api.example.com/v0/code/aB12@cezarmathe"));
        codeDb.setCreateTime(new Instant(1560354133));
        codeDb.setEditTime(new Instant(1560357733));

        System.out.println(codeDb.toJson());
    }
}
