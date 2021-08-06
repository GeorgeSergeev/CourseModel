package json;

import java.io.IOException;

/**
 * Маппинг обхектов в json
 */
public interface JsonMapper {
    <T> String mapObject(T object) throws IOException;
    <T> T mapJson(String json,Class<T> clazz) throws IOException;
}
