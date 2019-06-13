import com.hcodez.codeengine.model.Code;
import org.joda.time.Instant;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

public class CodeJsonTest {

    @Test
    public void jsonToCode() throws IOException {

        Code readCode = Code.fromJson(TestCommon.getResourceAsString("json/code.json"));
        System.out.println("Code: " + readCode);
        System.out.println("Created at: " + readCode.getCreateTime());
        System.out.println("Edited at: " + readCode.getEditTime());
        System.out.println("API URL: " + readCode.getUrl().toString());
        System.out.println("Code name: " + readCode.getName());
        System.out.println("Public status: " + readCode.getPublicStatus());

        Code goodCode = new Code();
        goodCode.setIdentifier("aB12");
        goodCode.setOwner("cezarmathe");
        goodCode.setPasscode("d723y7x28");
        goodCode.setName("A test code");
        goodCode.setPublicStatus(true);
        goodCode.setUrl(new URL("https://api.example.com/v0/code/aB12@cezarmathe"));
        goodCode.setCreateTime(new Instant(1560354133));
        goodCode.setEditTime(new Instant(1560357733));

        assert checkIdenticalCode(readCode, goodCode);
    }

    @Test
    public void codeToJson() throws IOException { // FIXME: 2019-06-13 proper test that checks values
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

    private static boolean checkIdenticalCode(Code a, Code b) {
        return a.getCreateTime().toString().equals(b.getCreateTime().toString()) &&
                a.getUrl().toString().equals(b.getUrl().toString()) &&
                a.getEditTime().toString().equals(b.getEditTime().toString()) &&
                a.getIdentifier().equals(b.getIdentifier()) &&
                a.getName().equals(b.getName()) &&
                a.getOwner().equals(b.getOwner()) &&
                a.getPasscode().equals(b.getPasscode());
    }

    private static boolean checkIdenticalJson(String a, String b) { // FIXME: 2019-06-13 this doesn't work
        a = a.replaceAll("\\s+", "");
        a = a.replaceAll("[\n\r]", "");

        b = b.replaceAll("\\s+", "");
        b = b.replaceAll("[\n\r]", "");

        return a.equals(b);
    }
}
