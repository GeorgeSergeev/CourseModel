package util;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    public synchronized void deSerializeStudentAndObjects(String json) throws IOException {
        reader = new StringReader(json);
        student = mapper.readValue(reader, Student.class);
        studentService.addStudent(student);
        student.getCourseStudentsGroups()
                .forEach(action -> {
            professorService.addProfessor(action.getCourse().getProfessor());
            courseService.addCourse(action.getCourse());
            courseService.addStudentToCourse(action.getCourse(), student);
            courseService.setProfessorForCourse(action.getCourse(), action.getCourse().getProfessor());
        });
        scores = new ArrayList<>(student.getScores());
        scores.forEach(action -> studentService.addScoreForStudent(action.getStudent(), action.getCourse(), action.getScore()));
    }

}
