package BeanSerializer;

import bean.Kyrs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by chuprovpa on 28.06.2017.
 */

/**
 * Получает строку JSON.
 */
public class KyrsSerializer {
    public static String serializKyrs(Kyrs st)
    {
        StringBuilder bs=new StringBuilder();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(st);
        bs.append(json);
        return bs.toString();
    }

    /**
     * Возвращает объект Kyrs
     * @param json
     * @return
     */
    public static Kyrs getKyrsObject(String json){
        Kyrs st = new Gson().fromJson(json, Kyrs.class);
        return st;
    }
}
