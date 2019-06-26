import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

class TestCommon {

    private static InputStream getResourceAsInputStream(String fileName) {
        InputStream inputStream = TestCommon.class.getResourceAsStream(fileName);

        assert inputStream != null;

        return inputStream;
    }

    static String getResourceAsString(String fileName) throws IOException {
        return CharStreams.toString(
                new InputStreamReader(getResourceAsInputStream(fileName),
                        StandardCharsets.UTF_8)
        );
    }
}
