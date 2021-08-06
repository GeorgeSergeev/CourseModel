package json;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class JsonFileWriterImpl implements JsonFileWriter {
    private final JsonMapper jsonMapper;

    public JsonFileWriterImpl(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    public  <T> void toJsonFile(String filename, T object) throws IOException
    {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(filename), false);
        String json = jsonMapper.mapObject(object);
        fileOutputStream.write(json.getBytes());
        fileOutputStream.close();
    }
}
