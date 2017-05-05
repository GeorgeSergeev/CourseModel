

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Created by TheEndl on 02.05.2017.
 */



public class University {



    public static void main(String[] args) {

        // Create Courses List
        ArrayList<Course> courses = new ArrayList<>();

               // Create Student
        Student student1 = new Student("Vasya", "Moscow", "+0123456789", "vasya@gmail.com", 1);


        Study studyMathForStudent1 = new Study(1,new ArrayList<Integer>());
        Course mathematicsMain = new Course("Mathematics", 1, 100);
        student1.studyInfo(mathematicsMain, studyMathForStudent1);
        CourseGroup groupMathematics = new CourseGroup("Mathematics", 1, 100);
        groupMathematics.addStudent(student1);
        studyMathForStudent1.setMarkForStudent(2);
        studyMathForStudent1.setMarkForStudent(3);
        studyMathForStudent1.setMarkForStudent(4);
        studyMathForStudent1.initMiddleGrade(student1);
        courses.add(groupMathematics);

        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

        File file = new File("JSON/result.json");
        file.getParentFile().mkdirs();
        try(Writer writer = new FileWriter(file))
        {

            gson.toJson(courses,writer);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader("JSON/result.json"));
            ArrayList<Course> coursesFromJson = gson.fromJson(reader,new TypeToken<ArrayList<Course>>() {}.getType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
