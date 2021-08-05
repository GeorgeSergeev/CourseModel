package ru.softlab.coursemodel.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.softlab.coursemodel.exception.OperationForbiddenException;
import ru.softlab.coursemodel.model.Course;
import ru.softlab.coursemodel.model.converter.CourseConverter;
import ru.softlab.coursemodel.model.dto.CourseDto;
import ru.softlab.coursemodel.model.dto.EnrollInCourseDto;
import ru.softlab.coursemodel.model.dto.StudentDto;
import ru.softlab.coursemodel.repository.CompletingCourseRepository;
import ru.softlab.coursemodel.repository.CourseRepository;
import ru.softlab.coursemodel.repository.StudentRepository;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static ru.softlab.coursemodel.service.CourseServiceTest.TestData.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    private CourseRepository repository;

    @Mock
    private CourseConverter converter;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentService studentService;

    @Mock
    private CompletingCourseRepository completingCourseRepository;

    @InjectMocks
    private CourseService service;

    @Test
    void enrollInCourse_shouldThrowOperationForbiddenException_whenInputDataIsValidAndStudentIsNotEnrolledInInputCourseBefore() {
        Integer studentId = studentOnCourseDto.getId();

        given(studentService.findById(studentId)).willReturn(studentOnCourseDto);
        given(repository.findById(studentId)).willReturn(Optional.of(course));
        given(converter.toDto(course)).willReturn(courseDto);

        OperationForbiddenException exception =
                assertThrows(OperationForbiddenException.class, () -> service.enrollInCourse(enrollInCourseDto));

        assertThat(exception.getMessage()).isEqualTo(
                "Student with id %s already enrolled in course with id %s",
                studentId, courseDto.getId());
        verify(repository, never()).bindStudentAndCourse(anyInt(), anyInt());
    }

    @Test
    void enrollInCourse_shouldEnrollStudentInCourse_whenInputDataIsValidAndStudentIsNotEnrolledInInputCourseBefore() {
        Integer studentId = studentDto.getId();

        given(studentService.findById(studentId)).willReturn(studentDto);
        given(repository.findById(studentId)).willReturn(Optional.of(course));
        given(converter.toDto(course)).willReturn(courseDto);
        doNothing().when(repository).bindStudentAndCourse(studentId, courseDto.getId());

        service.enrollInCourse(enrollInCourseDto);
    }

    interface TestData {
        EnrollInCourseDto enrollInCourseDto = EnrollInCourseDto.builder()
                .studentId(1)
                .courseId(1)
                .build();

        StudentDto studentDto = StudentDto.builder()
                .id(1)
                .version(0)
                .name("Bob")
                .phone("90001")
                .email("bob@mail.ru")
                .recordBookNumber(101)
                .courseIds(Collections.emptyList())
                .build();

        CourseDto courseDto = CourseDto.builder()
                .id(1)
                .version(0)
                .name("Java")
                .number(11)
                .price(49.99f)
                .build();

        Course course = Course.builder()
                .id(1)
                .version(0)
                .name("Java")
                .number(11)
                .price(49.99f)
                .build();

        StudentDto studentOnCourseDto = StudentDto.builder()
                .id(1)
                .version(0)
                .name("Bob")
                .phone("90001")
                .email("bob@mail.ru")
                .recordBookNumber(101)
                .courseIds(Collections.singleton(1))
                .build();
    }
}