package json;

import java.io.IOException;

public interface JsonFileReader {
    <T> T fromJsonFile(String filename, Class<T> clazz) throws IOException;
}
