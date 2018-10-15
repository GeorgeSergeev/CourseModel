package max.mustafin.controller;

import max.mustafin.model.Student;
import max.mustafin.service.StudentService;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/json")
public class JsonController {
    @Autowired
    StudentService studentService;
    @RequestMapping(value = "/toJson", method = RequestMethod.GET)
    public String objectToJson() {
        List<Student> allStudents = studentService.getAll();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationConfig.Feature.INDENT_OUTPUT,true);
        try {
            String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(allStudents);
            System.out.println(s);
            objectMapper.writeValue(new File("entities.json"),allStudents);     // file location {Application_Server_HOME}\bin\entities.json
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
    @RequestMapping(value = "/fromJson", method = RequestMethod.GET)
    public String jsonToObject () {
        ObjectMapper objectMapper = new ObjectMapper();
        Student[] students = null;
        File file = new File("entities.json");
        if (!file.exists()) {
            System.out.println("File not exists");
            return "failure";
        }
        else {
            try {
                students = objectMapper.readValue(new File("entities.json"), Student[].class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (students==null) {
            System.out.println("Could not deserialize objects");
            return "failure";
        }
        else {
            for (int i = 0; i < students.length; i++) {
                System.out.println(students[i]);
            }
        }
        return "success";
    }
}
