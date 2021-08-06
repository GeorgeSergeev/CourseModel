package json;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JacksonJsonMapper implements JsonMapper {
    private final ObjectMapper objectMapper=new ObjectMapper();

    public <T> String mapObject(T object) throws IOException {
        return this.objectMapper.writeValueAsString(object);
    }

    @Override
    public <T> T mapJson(String json,Class<T> clazz) throws IOException {
        return this.objectMapper.readValue(json,clazz);
    }
}
