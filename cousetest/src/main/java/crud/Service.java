package crud;


import com.sun.istack.internal.NotNull;
import json.JsonFileReader;
import json.JsonFileWriter;

import java.io.File;
import java.io.IOException;

/**
 * Простая реализация CRUD ервиса. В данном случае данные записываются и загружаются из json файлов.
 * Может быть заменён на сервис работающий с БД или другой.
 * В качестве ID используется hashCode
 * @param <C> тип обхекта
 */
public class Service<C> implements IService<Integer,C> {

    private final JsonFileReader jsonFileReader;
    private final JsonFileWriter jsonFileWriter;
    private final Class<C> clazz;
    private final String modelName;
    private final String folder;

    public Service(@NotNull JsonFileReader jsonFileReader,
                   @NotNull JsonFileWriter jsonFileWriter,
                   @NotNull Class<C> clazz,
                   @NotNull String modelName) {

        this(jsonFileReader,jsonFileWriter,clazz,modelName,"");
    }
    public Service(@NotNull JsonFileReader jsonFileReader,
                   @NotNull JsonFileWriter jsonFileWriter,
                   @NotNull Class<C> clazz,
                   @NotNull String modelName,
                   @NotNull String folder) {

        this.jsonFileReader = jsonFileReader;
        this.jsonFileWriter = jsonFileWriter;
        this.clazz=clazz;
        this.modelName=modelName;
        this.folder=folder;
    }
    public void create(@NotNull C object)
    {
        try {
            jsonFileWriter.toJsonFile(filename(object.hashCode()),object);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Cant write %s with id %d",modelName,object.hashCode()));
        }
    }
    public C load(@NotNull Integer id)
    {
        try {
            return jsonFileReader.fromJsonFile(filename(id),clazz);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Cant read %s with id %d",modelName,id));
        }
    }
    public void update(@NotNull C object)
    {
        create(object);
    }
    public void delete(@NotNull Integer id)
    {
        File file=new File(filename(id));
        file.delete();
    }

    private String filename(Integer id)
    {
        String result=String.format("%s/%s%s.json",this.folder,this.modelName,id.toString());
        return result.startsWith("/")?result.replaceFirst("/",""):result;
    }
}
