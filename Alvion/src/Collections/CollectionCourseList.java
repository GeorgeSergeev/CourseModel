package Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Course;
import model.CoursePassing;
import model.Student;

public class CollectionCourseList{

    private static ObservableList<Course> courseList = FXCollections.observableArrayList();

    public static void add(Course course) throws Exception{
        for (Course course1:
                courseList) {
            if (course.getNum() == course1.getNum()){
                //дописать
                throw new Exception();
            }
        }

        courseList.add(course);
    }

    public static void delete(Course course){

        for (CoursePassing coursePassing:
             CollectionCoursePassingList.getCoursePassingListList()) {
            if(coursePassing.getCourse().equals(course)){
                CollectionCoursePassingList.getCoursePassingListList().remove(coursePassing);
            }
        }

        courseList.remove(course);
    }

    public static void testDate() {
        try {
            add (new Course("Java",1,400f, null));
            add (new Course("C#",2,400f, null));
            add (new Course("ООП",3,400f, null));
            add (new Course("Реляционные базы данных",4,400f, null));
        }
        catch (Exception e){}
    }

    public static ObservableList<Course> getAccessibleCoursesByStudent(Student student){
        ObservableList<Course> AccesibleCourses = FXCollections.observableArrayList();

        for (Course course:
             getCourseList()) {
            AccesibleCourses.add(course);
        }

        for (CoursePassing coursePasing:
             CollectionCoursePassingList.getCoursePassingListList()) {
            if (coursePasing.getStudent() == student){
                AccesibleCourses.remove(coursePasing.getCourse());
            }
        }

        return AccesibleCourses;
    }

    public static ObservableList<Course> getCourseList(){
        return  courseList;
    }

    public static Course getCourseByNumAndName(String numAndName){
        try{
            String s[] = numAndName.split(",");
            int num = Integer.parseInt(s[0]);
            String name = s[1];

            for (Course course:
                    courseList) {
                if (course.getName().equals(name)&&course.getNum()==num){
                    return course;
                }
            }
        }
        catch (Exception e){}

        return null;
    }
    
    public static ObservableList<Course> getUnfinishedCoursesByStudentsNumAndName(String numAndName){
        try{
            String s[] = numAndName.split(",");
            int num = Integer.parseInt(s[0]);
            String name = s[1];
            ObservableList<Course> unfinishedCourse = FXCollections.observableArrayList();

            for (Student student:
                    CollectionStudentList.getStudentList()) {
                for (CoursePassing coursePassing:
                        CollectionCoursePassingList.getCoursePassingListList()) {
                    if(student.equals(coursePassing.getStudent())&&
                            !coursePassing.isFinished()&&
                            student.getNum() == num &&
                            student.getName().equals(name))
                        unfinishedCourse.add(coursePassing.getCourse());
                }
            }
            return unfinishedCourse;
        }
        catch (Exception e){
            return null;
        }
    }

    public static ObservableList<Course> getUnfinishedCoursesWithMarks(String studentNumAndName){
       try{
           String s[] = studentNumAndName.split(",");
           int num = Integer.parseInt(s[0]);
           String name = s[1];
           ObservableList<Course> unfinishedCourse = FXCollections.observableArrayList();

           for (Student student:
                   CollectionStudentList.getStudentList()) {
               for (CoursePassing coursePassing:
                       CollectionCoursePassingList.getCoursePassingListList()) {
                   if(student.equals(coursePassing.getStudent())&&
                           !coursePassing.isFinished()&&
                           coursePassing.getRating().size()!=0 &&
                           student.getNum() == num &&
                           student.getName().equals(name))
                       unfinishedCourse.add(coursePassing.getCourse());
               }
           }
           return unfinishedCourse;
       }
       catch (Exception e){
           return null;
       }
    }
}
