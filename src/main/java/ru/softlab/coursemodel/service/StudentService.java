package ru.softlab.coursemodel.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.softlab.coursemodel.model.Student;
import ru.softlab.coursemodel.model.converter.StudentConverter;
import ru.softlab.coursemodel.model.dto.StudentDto;
import ru.softlab.coursemodel.repository.StudentRepository;

@Slf4j
@Service
@Transactional
public class StudentService extends CrudServiceImpl<StudentDto,
        Student,
        StudentConverter,
        StudentRepository> {

    int findAveragePerformance(Student student) {

    }
}
