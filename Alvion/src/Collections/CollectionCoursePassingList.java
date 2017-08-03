package Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Course;
import model.CoursePassing;
import model.Student;

public class CollectionCoursePassingList{

    private static ObservableList<CoursePassing> coursePassingList = FXCollections.observableArrayList();

    public static void add(CoursePassing coursePassing){
        coursePassingList.add(coursePassing);
    }

    public static void delete(CoursePassing coursePassing){
        coursePassingList.remove(coursePassing);
    }

    public static ObservableList<CoursePassing> getCoursePassingListList(){
        return  coursePassingList;
    }

    public static CoursePassing getCoursePassingByStudentAndCourse(Student student, Course course){
        for (CoursePassing coursePassing:
             coursePassingList) {
            if(coursePassing.getStudent().equals(student)
                    && coursePassing.getCourse().equals(course)){
                return coursePassing;
            }
        }
        return null;
    }
}
