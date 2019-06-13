import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class TestCommon {

    public static InputStream getResourceAsInputStream(String fileName) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream(fileName);

        assert inputStream != null;

        return inputStream;
    }

    public static String getResourceAsString(String fileName) throws IOException {
        return CharStreams.toString(
                new InputStreamReader(getResourceAsInputStream(fileName),
                        StandardCharsets.UTF_8)
        );
    }
}
