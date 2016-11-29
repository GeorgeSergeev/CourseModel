/**
 * Created by Chuprov on 22.11.2016.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {
    private String name;
    private String address;
    private String phone;
    private String email;
    private Integer recordBookNum;
    private float averageMark;


    private static int id;

    private List<Course> courses = new ArrayList<>();
    private List<PassingCourse> passing = new ArrayList<>();
    public Student(){};

    public Student(String name, String address, String phone, String email) {
        //
        if ((name == null) || (address == null) || (phone == null) || (email==null)) {
            throw new RuntimeException("Некорректный ввод данных для объекта 'Студент'");
        }
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.recordBookNum = ++id;
    }


    public void addStudent(Course course) {
        if ((course != null) && !courses.contains(course)) {
            courses.add(course);
            passing.add(new PassingCourse(course));
            course.addStudent(this);
        }
    }

    public void removeStudent(Course course) {
        if ((course == null) || !courses.contains(course)) {
            return;
        }
        //Если курс завершен(есть финальная оценка) - мы не можем удалить студента
        PassingCourse pass = getPassingCourse(course);
        if (pass.getFinalMark() == null) {
            courses.remove(course);
            passing.remove(pass);
            course.removeStudent(this);
        }
    }

    public List<Course> getListCompletedCourses() {
        List<Course> result = new ArrayList<>();
        for (PassingCourse pass : passing) {
            // Курс завершен, если имеет финальную оценку
            if (pass.getFinalMark() != null) {
                result.add(pass.getCourse());
            }
        }
        return result;
    }

    public PassingCourse getPassingCourse(Course course) {
        if (course != null) {
            for (PassingCourse pass : passing) {
                if (course.equals(pass.getCourse())) {
                    return pass;
                }
            }
        }
        return null;
    }

    public void getAveregeMarkFromAllCourses() {
        float sum = 0;
        float size =courses.size();
        for (PassingCourse pass : passing) {
            Integer mark;
            // Если есть финальная оценка - посчитать их сумму
            if ((mark = pass.getFinalMark()) != null) {
                sum += mark;

            }
        }
        averageMark = (size > 0) ? ((1.0f * sum) / size) : 0;
    }

    @Override
    public String toString() {

        return  "Имя " +
                name +"\n"+
                "Адресс = " +
                address +"\n"+
                "Телефон = " +
                phone +"\n"+
                "email = " +
                email +"\n"+
                "Номер зачётки = " +
                recordBookNum ;
    }
}