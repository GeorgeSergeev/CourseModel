package CourseModel;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by TheEndl on 04.05.2017.
 */
public class Mathematik extends Course {
    public ArrayList<Student> studentsList = new ArrayList<>();

    public Mathematik(String courseName, int courseNumber, float courseCost) {
        super(courseName, courseNumber, courseCost);
    }
     // Add student to course
    public void addStudentToCourse(Student student)
    {
        studentsList.add(student);

    }
    // Delete student from course
    public void deleteStudent(Student student)
    {
        studentsList.remove(student);
    }
    // Returns list of students on this course
    public ArrayList getStudentsList()
    {
        Iterator studentsIterator = studentsList.iterator();
        while (studentsIterator.hasNext())
        {
            Student student = (Student)studentsIterator.next();

        }
        System.out.println(studentsList);
        return studentsList;
    }
}
