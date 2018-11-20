import com.fasterxml.jackson.databind.ObjectMapper;
import model.Course;
import model.Professor;
import model.Student;
import model.StudentStatus;
import service.CourseService;
import service.ProfessorService;
import service.StudentService;
import util.SerializeProcessor;

import java.io.IOException;

public class Main {

    StudentService studentService = new StudentService();
    ProfessorService professorService = new ProfessorService();
    CourseService courseService = new CourseService();

    SerializeProcessor serializeProcessor = SerializeProcessor.getInstance();

    public static void main(String[] args) {

        Main main = new Main();

        System.out.println("START TEST");

        Student student1 = new Student("Vasily", "Crimea", "02", "admin@fbi.gov", 16);
        Student student2 = new Student("Kolya", "Moscow", "03", "user@fbi.gov", 22);
        Student student3 = new Student("Emelya", "Novosibirsk", "04", "nospam@fbi.gov", 41);

        Professor professor1 = new Professor("prof.Lomonosov", "Moscow", "01", 50000f);
        Professor professor2 = new Professor("prof.Ivanov", "Piter", "02", 45000f);
        Professor professor3 = new Professor("prof.Petrov", "Murmansk", "03", 15000f);

        Course course1 = new Course("Java course", 25000f);
        Course course2 = new Course("Android course", 55000f);
        Course course3 = new Course("C# course", 40000f);

        main.studentService.addStudent(student1);
        main.studentService.addStudent(student2);
        main.studentService.addStudent(student3);

        main.professorService.addProfessor(professor1);
        main.professorService.addProfessor(professor2);
        main.professorService.addProfessor(professor3);

        main.courseService.addCourse(course1);
        main.courseService.addCourse(course2);
        main.courseService.addCourse(course3);

        main.courseService.setProfessorForCourse(course1, professor1);
        main.courseService.setProfessorForCourse(course2, professor3);

        main.courseService.addStudentToCourse(course1, student1);
        main.courseService.addStudentToCourse(course1, student2);
        main.courseService.addStudentToCourse(course1, student3);

        main.courseService.changeStudentStatusOnCourse(course1, student2, StudentStatus.LISTENING);

        main.studentService.addScoreForStudent(student1, course1, 5);

        String s = null;
        try {
            s = main.serializeProcessor.serializeStudent(student1);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            main.serializeProcessor.deSerializeStudentAndObjects(s);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("END TEST");
    }
}
