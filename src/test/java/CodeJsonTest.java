import com.hcodez.codeengine.model.Code;
import org.joda.time.Instant;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CodeJsonTest {

    @Test
    public void jsonToCode() throws MalformedURLException {

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream("json/code.json");

        assert inputStream != null;

        Code readCode = Code.fromJson(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        System.out.println("Code: " + readCode);
        // FIXME: 2019-06-12 code not loading timestamps from json
        System.out.println("Created at: " + readCode.getCreateTime());
        System.out.println("Edited at: " + readCode.getEditTime());
        System.out.println("API URL: " + readCode.getUrl().toString());

        Code goodCode = new Code();
        goodCode.setIdentifier("aB12");
        goodCode.setOwner("cezarmathe");
        goodCode.setPasscode("d723y7x28");
        goodCode.setName("A test code");
        goodCode.setPublicStatus(true);
        goodCode.setUrl(new URL("https://api.example.com/v0/code/aB12@cezarmathe"));
        goodCode.setCreateTime(new Instant(1560354133));
        goodCode.setEditTime(new Instant(1560357733));

//        assert readCode.equals(goodCode);
    }

    @Test
    public void codeToJson() throws MalformedURLException {
        Code code = new Code();
        code.setIdentifier("aB12");
        code.setOwner("cezarmathe");
        code.setPasscode("d723y7x28");
        code.setName("A test code");
        code.setPublicStatus(true);
        code.setUrl(new URL("https://api.example.com/v0/code/aB12@cezarmathe"));
        code.setCreateTime(new Instant(1560354133));
        code.setEditTime(new Instant(1560357733));

        System.out.println(code.toJson());
    }
}
