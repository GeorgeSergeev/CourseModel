package JSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import Collections.CollectionCourseList;
import Collections.CollectionCoursePassingList;
import Collections.CollectionProfessorList;
import Collections.CollectionStudentList;
import model.Course;
import model.CoursePassing;
import model.Professor;
import model.Student;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class JsonWorker {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static void studentsToJson(){

        File file = new File("jsonFiles/Students.json");

        try{
            FileWriter fw = new FileWriter(file);

            ArrayList<Student> students = new ArrayList<>(CollectionStudentList.getStudentList());

            fw.write(GSON.toJson(students));

            fw.close();
        }
        catch (IOException e){
        }

    }

    private static void studentsFromJson(){

        File file = new File("jsonFiles/Students.json");

        try{
            FileReader fr= new FileReader(file);

            StringBuffer s = new StringBuffer("");

            int c;
            while((c=fr.read())!=-1){
                s.append((char) c);
            }

            CollectionStudentList.getStudentList().clear();

            ArrayList<Student> students= GSON.fromJson(s.toString(), new TypeToken<ArrayList<Student>>(){}.getType());

             for (Student student:
                 students) {
                CollectionStudentList.add(student);
            }

            fr.close();
        }
        catch (Exception e){
        }
    }

    private static void professorsToJson(){

        File file = new File("jsonFiles/Professors.json");

        try{
            FileWriter fw = new FileWriter(file);

            ArrayList<Professor> professors = new ArrayList<>(CollectionProfessorList.getProfessorList());

            fw.write(GSON.toJson(professors));

            fw.close();
        }
        catch (IOException e){
        }

    }

    private static void professorsFromJson(){

        File file = new File("jsonFiles/Professors.json");

        try{
            FileReader fr= new FileReader(file);

            StringBuffer s = new StringBuffer("");

            int c;
            while((c=fr.read())!=-1){
                s.append((char) c);
            }

            CollectionProfessorList.getProfessorList().clear();

            ArrayList<Professor> professors= GSON.fromJson(s.toString(), new TypeToken<ArrayList<Professor>>(){}.getType());

            for (Professor professor:
                    professors) {
                CollectionProfessorList.add(professor);
            }

            fr.close();
        }
        catch (Exception e){
        }

    }

    private static void coursesToJson(){

        File file = new File("jsonFiles/Courses.json");

        try{
            FileWriter fw = new FileWriter(file);

            ArrayList<Course> courses = new ArrayList<>(CollectionCourseList.getCourseList());

            fw.write(GSON.toJson(courses));

            fw.close();
        }
        catch (IOException e){
        }
    }

    private static void coursesFromJson(){

        File file = new File("jsonFiles/Courses.json");

        try{
            FileReader fr= new FileReader(file);

            StringBuffer s = new StringBuffer("");

            int c;
            while((c=fr.read())!=-1){
                s.append((char) c);
            }

            CollectionCourseList.getCourseList().clear();

            ArrayList<Course> courses= new Gson().fromJson(s.toString(), new TypeToken<ArrayList<Course>>(){}.getType());

            for (Course course:
                    courses) {
                CollectionCourseList.add(course);
            }

            fr.close();
        }
        catch (Exception e){
        }
    }

    private static void coursesPassingToJson(){

        File file = new File("jsonFiles/CoursesPassing.json");

        try{
            FileWriter fw = new FileWriter(file);

            ArrayList<CoursePassing> coursesPassing = new ArrayList<>(CollectionCoursePassingList
                                                                            .getCoursePassingListList());

            fw.write(GSON.toJson(coursesPassing));

            fw.close();
        }
        catch (IOException e){
        }

    }

    private static void coursesPassingFromJson(){

        File file = new File("jsonFiles/CoursesPassing.json");

        try{
            FileReader fr= new FileReader(file);

            StringBuffer s = new StringBuffer("");

            int c;
            while((c=fr.read())!=-1){
                s.append((char) c);
            }

            CollectionCoursePassingList.getCoursePassingListList().clear();

            ArrayList<CoursePassing> coursesPassing= GSON.fromJson(s.toString(), new TypeToken<ArrayList<CoursePassing>>(){}.getType());

            for (CoursePassing coursePassing:
                    coursesPassing) {
                CollectionCoursePassingList.add(coursePassing);
            }

            fr.close();
        }
        catch (Exception e){
        }
    }

    public static void toJson(){
        studentsToJson();
        professorsToJson();
        coursesToJson();
        coursesPassingToJson();
    }

    public static void fromJson(){
        studentsFromJson();
        professorsFromJson();
        coursesFromJson();
        coursesPassingFromJson();
    }

    public static void main(String args[]){
        fromJson();
    }
}
