package json;

import java.io.IOException;

public interface JsonFileWriter {
    <T> void toJsonFile(String filename, T object) throws IOException;
}
