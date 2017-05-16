import crud.IService;
import crud.Service;
import domain.Professor;
import domain.Student;
import domain.course.Course;
import domain.course.ICourse;
import domain.course.ProfessorDependentCourse;
import json.JacksonJsonMapper;
import json.JsonFileReaderImpl;
import json.JsonFileWriterImpl;
import json.JsonMapper;

public class Main {
    public static void main(String[] args) {
            //Класс для аппинга объектов в json
            JsonMapper jsonMapper = new JacksonJsonMapper();
            //Классы для созранения обхектов в json файл
            JsonFileWriterImpl jsonFileWriter=new JsonFileWriterImpl(jsonMapper);
            JsonFileReaderImpl jsonFileReader=new JsonFileReaderImpl(jsonMapper);

            //Создаём CRUD сервис для сохранения данных профессора.
            //По аналогии создаются сервисы и для других моделей.
            IService<Integer,Professor> professorService=new Service<>(jsonFileReader,jsonFileWriter,Professor.class,"professor");

            //Заполняем данные
            Professor professor =new Professor("Egor","Pushkina","123123",123123);
            ICourse course = new Course("Test course", 100, 123);
            ICourse professorCourse = new ProfessorDependentCourse(course, professor);
            Student student = new Student("Vasiliy", "Pushkina 14", "vasiliy@gmail.com", "123-45-67", "1");
            course.addStudent(student);

            //Создаём запись о профессоре.(По аналогии выполняются и дугие опеции см. интерфейс IService
            professorService.create(professor);
    }
}
