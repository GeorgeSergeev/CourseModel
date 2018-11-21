package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Course;
import model.Professor;
import model.Score;
import model.Student;
import service.CourseService;
import service.ProfessorService;
import service.StudentService;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class SerializeProcessor {

    private StringWriter writer = new StringWriter();
    private StringReader reader;
    private List<Score> scores;
    private ObjectMapper mapper = new ObjectMapper();
    private Student student;

    private CourseService courseService = new CourseService();
    private ProfessorService professorService = new ProfessorService();
    private StudentService studentService = new StudentService();

    private static SerializeProcessor ourInstance = new SerializeProcessor();

    public static SerializeProcessor getInstance() {
        return ourInstance;
    }

    private SerializeProcessor() {
    }

    public String serializeStudent(Student student) throws IOException {
        mapper.writeValue(writer, student);
        return writer.toString();
    }

    public synchronized void deSerializeStudentAndObjects(String json, boolean addScores) throws IOException {
        reader = new StringReader(json);
        student = mapper.readValue(reader, Student.class);
        Student newStudent = new Student(student);
        studentService.addStudent(newStudent);
        student.getCourseStudentsGroups()
                .forEach(action -> {
                    Professor newProfessor = professorService.getById(action.getCourse().getProfessor().getId());
                    if (newProfessor == null) {
                        newProfessor = new Professor(action.getCourse().getProfessor());
                        professorService.addProfessor(newProfessor);
                    }
                    Course newCourse = courseService.getById(action.getCourse().getId());
                    if (newCourse == null) {
                        newCourse = new Course(action.getCourse());
                        courseService.addCourse(newCourse);
                    }
                    courseService.addStudentToCourse(newCourse, newStudent);
                    courseService.setProfessorForCourse(newCourse, newProfessor);
                });
        if (addScores) {
            scores = new ArrayList<>(student.getScores());
            scores.forEach(action -> {
                Course newCourse = courseService.getById(action.getCourse().getId());
                if (newCourse == null) {
                    newCourse = new Course(action.getCourse());
                    courseService.addCourse(newCourse);
                }
                studentService.addScoreForStudent(newStudent, newCourse, action.getScore());
            });
        }
    }

}
