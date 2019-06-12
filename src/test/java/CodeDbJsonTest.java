import com.hcodez.codeengine.model.CodeDb;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CodeDbJsonTest {

    @Test
    public void jsonToCode() {

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream("json/code_db.json");

        assert inputStream != null;

        CodeDb codeDb = CodeDb.fromJson(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        System.out.println("Code: " + codeDb);
        System.out.println("Code ID: " + codeDb.getId());
        System.out.println("Code owner ID: " + codeDb.getOwnerId());
        // FIXME: 2019-06-12 code not loading timestamps from json
//        System.out.println("Created at: " + codeDb.getCreateTime().toString());
//        System.out.println("Edited at: " + codeDb.getEditTime().toString());
        System.out.println("API URL: " + codeDb.getUrl().toString());
    }

    @Test
    public void codeToJson() {
        CodeDb code = new CodeDb();
        code.setIdentifier("aB12");
        code.setOwner("cezarmathe");
        code.setPublicStatus(true);
        System.out.println(code.toJson());
    }
}
