package BeanSerializer;

import bean.Professor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by chuprovpa on 28.06.2017.
 * Сериализация и десериализация для класса Professor
 */
public class ProfessorSerializer {

    public static String serializProfessor(Professor st)
    {
        StringBuilder bs=new StringBuilder();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(st);
        bs.append(json);
        return bs.toString();
    }

    public static Professor getProfessorObject(String json){
        Professor st = new Gson().fromJson(json, Professor.class);
        return st;
    }
}
