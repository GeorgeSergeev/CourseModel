package json;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class JsonFileReaderImpl implements JsonFileReader {
    private final JsonMapper jsonMapper;

    public JsonFileReaderImpl(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    public <T> T fromJsonFile(String filename, Class<T> clazz) throws IOException
    {
        File file = new File(filename);
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();
        return jsonMapper.mapJson(new String(data), clazz);
    }
}
