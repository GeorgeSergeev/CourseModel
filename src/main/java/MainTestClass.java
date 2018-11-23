import model.Course;
import model.Professor;
import model.Student;
import model.StudentStatus;
import service.CourseService;
import service.ProfessorService;
import service.StudentService;
import util.SerializeProcessor;
import util.Services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Тестовый класс для проверки работы с DAO
 */

public class MainTestClass {

    Path jsonFile = Paths.get("json.txt");

    SerializeProcessor serializeProcessor = SerializeProcessor.getInstance();

    public static void main(String[] args) {

        StudentService studentService = Services.getInstance().getStudentService();
        ProfessorService professorService = Services.getInstance().getProfessorService();
        CourseService courseService = Services.getInstance().getCourseService();

        MainTestClass mainTestClass = new MainTestClass();

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

        studentService.addStudent(student1);
        studentService.addStudent(student2);
        studentService.addStudent(student3);

        professorService.addProfessor(professor1);
        professorService.addProfessor(professor2);
        professorService.addProfessor(professor3);

        courseService.addCourse(course1);
        courseService.addCourse(course2);
        courseService.addCourse(course3);

        courseService.setProfessorForCourse(course1, professor1);
        courseService.setProfessorForCourse(course2, professor3);

        courseService.addStudentToCourse(course1, student1);
        courseService.addStudentToCourse(course2, student1);
        courseService.addStudentToCourse(course1, student2);
        courseService.addStudentToCourse(course1, student3);

        courseService.changeStudentStatusOnCourse(course1, student2, StudentStatus.LISTENING);

        studentService.addScoreForStudent(student1, course1, 5);
        studentService.addScoreForStudent(student1, course1, 4);
        studentService.addScoreForStudent(student1, course2, 4);
        studentService.addScoreForStudent(student1, course2, 4);

        studentService.addScoreForStudent(student2, course1, 3);
        studentService.addScoreForStudent(student2, course1, 3);

        System.out.println("---");
        System.out.println(student1.calculateAverageScore());
        System.out.println(student1.averageScoreForCourse(course1));
        System.out.println("---");

        courseService.changeStudentStatusOnCourse(course1, student2, StudentStatus.GRADUATED);

        System.out.println(student2.graduatedCourses().size());
        System.out.println(student2.graduatedCourses().get(0).getName());

//        courseService.removeStudentFromCourse(course1, student2);

//        studentService.removeAllScoresForStudentOnCourse(student2, course1);

/*        courseService.removeStudentFromCourse(course1, student1);
        courseService.removeStudentFromCourse(course2, student1);
        studentService.removeAllScoresForStudentOnCourse(student1, course1);
        studentService.removeAllScoresForStudentOnCourse(student1, course2);*/

        studentService.removeStudent(student1);

        professorService.removeProfessor(professor3);


/*        System.out.println("== SERIALIZATION ==");

        String s = null;
        try {
            s = mainTestClass.serializeProcessor.serializeStudent(student1);
            System.out.println(s);
            Files.createFile(mainTestClass.jsonFile);
            Files.write(mainTestClass.jsonFile, s.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            List<String> strings = Files.readAllLines(mainTestClass.jsonFile);
            mainTestClass.serializeProcessor.deSerializeStudentAndObjects(strings.get(0), true);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        System.out.println("END TEST");
    }
}
