package BeanSerializer;

import bean.Student;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by chuprovpa on 28.06.2017.
 */
public class StudentSerializer {
   public static String serializStudent(Student st)
   {
       StringBuilder bs=new StringBuilder();
       Gson gson = new GsonBuilder()
               .setPrettyPrinting()
               .create();
       String json = gson.toJson(st);
      bs.append(json);
       return bs.toString();
   }

   public static Student getStudentObject(String json){
    Student st = new Gson().fromJson(json, Student.class);
   return st;
   }
}
