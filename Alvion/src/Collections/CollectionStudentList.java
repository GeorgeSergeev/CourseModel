package Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CoursePassing;
import model.Student;

public class CollectionStudentList{
    private static ObservableList<Student> studentList = FXCollections.observableArrayList();

    public static void add(Student student) throws Exception{

        for (Student stud:
             studentList) {
            if (stud.getNum()==student.getNum()){
                //дописать
                throw new Exception();
            }
        }
        studentList.add(student);
    }

    public static void delete(Student student){

        for (CoursePassing coursePassing:
             CollectionCoursePassingList.getCoursePassingListList()) {
            if (coursePassing.getStudent().equals(student)){
                CollectionCoursePassingList.getCoursePassingListList().remove(coursePassing);
            }
        }

        studentList.remove(student);
    }

    public static void testDate(){
        studentList.add(new Student("Подмаско К.В.","SEVASTOPOL","+4","fsfs",1));

        studentList.add(new Student("Супрунов В.С.","SEVASTOPOL","+4","fsfs",2));

        studentList.add(new Student("Яндульский А.В.","SEVASTOPOL","+4","fsfs",3));

        studentList.add(new Student("Сосулька С.С.","SEVASTOPOL","+4","fsfs",4));

        studentList.add(new Student("Письянов","SEVASTOPOL","+4","fsfs",5));
    }

    public static ObservableList<Student> getStudentList(){
        return  studentList;
    }

    public static Student getStudentByNumAndName (String numAndName){
        try {
            String s[] = numAndName.split(",");
            int num = Integer.parseInt(s[0]);
            String name = s[1];


        for (Student student:
             studentList) {
            if (student.getName().equals(name)&&student.getNum()==num){
                return student;
            }
            }
        }
        catch (Exception e){}
        return null;
    }

    public static ObservableList<Student> getStudentsWithUnfinishedCourses(){
        ObservableList<Student> studentsWithUnfinishedCourses = FXCollections.observableArrayList();

        for (Student student:
             studentList) {
            for (CoursePassing coursePassing:
                 CollectionCoursePassingList.getCoursePassingListList()) {
                if ((coursePassing.getStudent().equals(student))&&
                        (!coursePassing.isFinished())){
                    studentsWithUnfinishedCourses.add(student);
                    break;
                }
            }
        }
        return studentsWithUnfinishedCourses;
    }
}
