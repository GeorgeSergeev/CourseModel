package util;

import lombok.Getter;
import service.CourseService;
import service.ProfessorService;
import service.StudentService;

public class Services {

    @Getter
    private StudentService studentService;

    @Getter
    private ProfessorService professorService;

    @Getter
    private CourseService courseService;

    private static Services ourInstance = new Services();

    public static Services getInstance() {
        return ourInstance;
    }

    private Services() {
        this.studentService = new StudentService();
        this.professorService = new ProfessorService();
        this.courseService = new CourseService();
    }
}
