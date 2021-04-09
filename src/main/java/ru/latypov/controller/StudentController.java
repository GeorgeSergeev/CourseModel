package ru.latypov.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.latypov.exception.StudentNotFound;
import ru.latypov.model.Student;
import ru.latypov.service.StudentService;

import java.util.List;


/**
 * Контролер для  api/student.
 */

@RestController
@RequestMapping("api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * Слушаем /list.
     */
    @PostMapping(value = "/list")
    public List<Student> getStudent() {
        List<Student> student = studentService.retrieveStudent();
        return student;
    }

    /**
     * Слушаем /{id}.
     */
    @GetMapping(value = "/{id}")
    @ResponseBody
    public Student getStudent (@PathVariable(name = "id") Integer id) throws StudentNotFound {
  
        return studentService.getStudent(id);
    }

    /**
     * Слушаем /update.
     */
    @PostMapping(value = "/update")
    public ResponseEntity updateStudent(@RequestBody Student student) {
        Student emp = studentService.getStudent(student);
        if (emp != null) {
            studentService.updateStudent(student);

        }

        return new ResponseEntity("success", HttpStatus.OK);
    }


    /**
     * Слушаем /save.
     */
    @PostMapping(value = "/save")
    public void saveStudent(Student student) {
        studentService.saveStudent(student);

    }

}



