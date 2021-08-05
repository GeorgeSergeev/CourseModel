package ru.softlab.coursemodel.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.softlab.coursemodel.model.Course;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.softlab.coursemodel.repository.CourseRepositoryTest.TestData.courses;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = RepositoryTestConfig.class)
@Sql({"/drop-test-data.sql", "/test-course-data.sql"})
class CourseRepositoryTest {

    @Autowired
    private CourseRepository repository;

    @Test
    void findAllByStudentIdAndFinalMarkIsNotNull_shouldReturnEmptyCollection_whenDatabaseDoesNotContainAnyCourseWhichHasNotFinishedByStudentWithInputId() {
        Integer studentId = 1;

        Collection<Course> actual = repository.findAllByStudentIdAndFinalMarkIsNotNull(studentId);
        assertThat(actual).isEmpty();
    }

    @Test
    void findAllByStudentIdAndFinalMarkIsNotNull_shouldReturnNotEmptyCollection_whenDatabaseContainsAnyCourseWhichHasFinishedByStudentWithInputId() {
        Integer studentId = 2;
        List<Course> expected = courses.subList(1, 2);

        Collection<Course> actual = repository.findAllByStudentIdAndFinalMarkIsNotNull(studentId);
        assertThat(actual)
                .isNotEmpty()
                .hasSize(1).usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(expected);
    }

    interface TestData {
        List<Course> courses = List.of(
                Course.builder()
                        .id(1)
                        .version(0)
                        .name("Java")
                        .number(11)
                        .price(49.99f)
                        .build(),
                Course.builder()
                        .id(2)
                        .version(0)
                        .name("Kotlin")
                        .number(12)
                        .price(59.99f)
                        .build());
    }
}