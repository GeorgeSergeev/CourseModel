package org.bajiepka.courseApp.converter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.bajiepka.courseApp.services.FileService;
import org.bajiepka.courseApp.wrappers.RootHolder;

public class MyJsonConverter {

    private FileService fileService = new FileService();

    public MyJsonConverter() {
    }

    private RootHolder rootHolder;

    public void addForConversion(RootHolder rootHolder){
        this.rootHolder = rootHolder;
    }

    public String write(boolean toFile){

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter().withRootName("import");

        try {
            String result = writer.writeValueAsString(rootHolder);
            if (toFile){
                result = fileService.writeToFile(result);
            }

            return result;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";

    }
}
