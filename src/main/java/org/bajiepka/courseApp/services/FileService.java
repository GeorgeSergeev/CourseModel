package org.bajiepka.courseApp.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bajiepka.courseApp.wrappers.RootHolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FileService {

    // TODO Осторожно хардкод! разобраться почему не аутовайрятся свойства.
    private final String directory = "D:\\";
    private final String prefix = "json";

    public String writeToFile(String content){

        String path = String.format("%s%s_%s.json", directory, prefix, new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss").format(new Date()));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))){
            writer.write(content);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public String readFile(String filepath){

        File newFile = new File(filepath);
        if (newFile.exists()) {

            try (FileInputStream inputStream = new FileInputStream(newFile)){

                byte data[] = new byte[inputStream.available()];
                inputStream.read(data);
                return new String(data);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "";

    }
}
