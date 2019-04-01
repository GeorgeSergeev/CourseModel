package org.bajiepka.courseApp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.bajiepka.courseApp.converter.MyJsonConverter;
import org.bajiepka.courseApp.domain.Student;
import org.bajiepka.courseApp.repositories.StudentRepository;
import org.bajiepka.courseApp.wrappers.RootHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;

@Component
public class StudentService {

    private StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.repository = studentRepository;
    }

    public List<Student> getStudents(){
        return (List<Student>) repository.findAll();
    }

    public Student getStudentById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public String viewStudentAsJSON(Long id){

        String result = "...";

        try {

            Student student = getStudentById(id);
            ObjectWriter mapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return mapper.writeValueAsString(student);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void dismissStudent(Long id){
        Student dismissive = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ошибка! Такой студент не существует!"));
        repository.deleteById(dismissive.getId());
    }

    public boolean saveStudent(Student student) {
        repository.save(student);
        return true;
    }

    public boolean updateStudent(Student student) {

        Student currentStudent = getStudentById(student.getId());
        currentStudent.setId(student.getId());
        currentStudent.setVersion(student.getVersion() + 1);
        currentStudent.setName(student.getName());
        currentStudent.setPhone(student.getPhone());
        currentStudent.setAddress(student.getAddress());
        saveStudent(currentStudent);

        return true;
    }

    public String viewAllStudentAsJSON(boolean toFile) {

        RootHolder rootHolder = new RootHolder(
                repository.findAll(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList());

        MyJsonConverter converter = new MyJsonConverter();
        converter.addForConversion(rootHolder);
        return converter.write(toFile);
    }
}
