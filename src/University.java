package CourseModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by TheEndl on 02.05.2017.
 */



public class University {



    public static void main(String[] args) {

        // Create Courses List
        ArrayList<Course> courses = new ArrayList<>();
            //test//
        int i;
        // Create new Course - Mathematics
        Mathematik mathematics = new Mathematik("Mathematics", 1, 100);

        // Create Student
        Student student1 = new Student("Vasya", "Moscow", "+0123456789", "vasya@gmail.com", 1);


        Study studyMathForStudent1 = new Study();
        Course mathematicsMain = new Course("Mathematics", 1, 100);

        studyMathForStudent1.setMarkForStudent(student1,2);
        studyMathForStudent1.setMarkForStudent(student1,3);
        studyMathForStudent1.setMarkForStudent(student1,4);

        student1.studyInfo(mathematicsMain, studyMathForStudent1);
        mathematics.addStudentToCourse(student1);
        courses.add(mathematics);

        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
        String json = gson.toJson(courses);
        try(FileWriter writer = new FileWriter("C:\\Users\\Public\\Documents\\Courses.json"))
        {
            gson.toJson(courses,writer);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Public\\Documents\\Courses.json"));
            ArrayList<Course> coursesFromJson = gson.fromJson(reader,new TypeToken<ArrayList<Course>>() {}.getType());
            System.out.println(coursesFromJson);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
